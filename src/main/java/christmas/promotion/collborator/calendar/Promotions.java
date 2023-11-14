package christmas.promotion.collborator.calendar;

import christmas.promotion.collborator.calendar.benefit.BenefitAmount;
import christmas.promotion.collborator.calendar.benefit.DDayBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.GiveawayBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.SpecialBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.WeekdayBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.WeekendBenefitWrapper;
import christmas.promotion.collborator.menu.Menu;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.exception.InvalidReservationOrderException;
import java.util.List;
import java.util.Optional;

public class Promotions {

    private static final int CRITERIA_AMOUNT_FOR_EVENT = 10_000;
    private static final int CRITERIA_AMOUNT_FOR_GIVEAWAY = 120_000;
    private static final int DEFAULT_AMOUNT_OF_D_DAY_DISCOUNT = 1_000;
    private static final int INCREASE_AMOUNT_OF_D_DAY_DISCOUNT = 100;
    private static final int AMOUNT_OF_WEEK_DISCOUNT = 2_023;
    private static final int AMOUNT_OF_SPECIAL_DISCOUNT = 1_000;

    private final Date today;
    private final Orders orders;
    private final int totalPaymentAmount;

    protected Promotions(Date todayDate, Orders orders) {
        this.today = todayDate;
        this.orders = orders;
        this.totalPaymentAmount = orders.calculateTotalPrice();
    }

    public BenefitAmount askBenefitAmount() {
        if (totalPaymentAmount < CRITERIA_AMOUNT_FOR_EVENT) {
            return new BenefitAmount(List.of(new GiveawayBenefitWrapper(Optional.empty()),
                    new DDayBenefitWrapper(Optional.empty()),
                    new WeekendBenefitWrapper(Optional.empty()),
                    new WeekdayBenefitWrapper(Optional.empty()),
                    new SpecialBenefitWrapper(Optional.empty())));
        }
        return new BenefitAmount(List.of(new GiveawayBenefitWrapper(calculateGiveAwayAmount()),
                new DDayBenefitWrapper(calculateDDayAmount()),
                new WeekendBenefitWrapper(calculateWeekendAmount()),
                new WeekdayBenefitWrapper(calculateWeekdayAmount()),
                new SpecialBenefitWrapper(calculateSpecialAmount())));
    }

    private Optional<Integer> calculateGiveAwayAmount() {
        if (totalPaymentAmount < CRITERIA_AMOUNT_FOR_GIVEAWAY) {
            return Optional.empty();
        }
        return Optional.of(
                Menu.findBy("샴페인")
                        .orElseThrow(InvalidReservationOrderException::new)
                        .getPrice());
    }

    private Optional<Integer> calculateDDayAmount() {
        if (!today.contains(Promotion.D_DAY)) {
            return Optional.empty();
        }
        final int dayOfIncrease = today.date() - 1;
        return Optional.of(
                DEFAULT_AMOUNT_OF_D_DAY_DISCOUNT + (dayOfIncrease * INCREASE_AMOUNT_OF_D_DAY_DISCOUNT));
    }

    private Optional<Integer> calculateWeekendAmount() {
        if (!today.contains(Promotion.WEEKEND)) {
            return Optional.empty();
        }
        return Optional.of(
                orders.countMainOrders() * AMOUNT_OF_WEEK_DISCOUNT);
    }

    private Optional<Integer> calculateWeekdayAmount() {
        if (!today.contains(Promotion.WEEKDAY)) {
            return Optional.empty();
        }
        return Optional.of(
                orders.countDessertOrders() * AMOUNT_OF_WEEK_DISCOUNT);
    }

    private Optional<Integer> calculateSpecialAmount() {
        if (!today.contains(Promotion.SPECIAL)) {
            return Optional.empty();
        }
        return Optional.of(AMOUNT_OF_SPECIAL_DISCOUNT);
    }

}
