package christmas.promotion.collborator.calendar;

import static christmas.promotion.collborator.calendar.PromotionsAmount.AMOUNT_OF_SPECIAL_DISCOUNT;
import static christmas.promotion.collborator.calendar.PromotionsAmount.AMOUNT_OF_WEEK_DISCOUNT;
import static christmas.promotion.collborator.calendar.PromotionsAmount.CRITERIA_AMOUNT_FOR_EVENT;
import static christmas.promotion.collborator.calendar.PromotionsAmount.CRITERIA_AMOUNT_FOR_GIVEAWAY;
import static christmas.promotion.collborator.calendar.PromotionsAmount.DEFAULT_AMOUNT_OF_D_DAY_DISCOUNT;
import static christmas.promotion.collborator.calendar.PromotionsAmount.INCREASE_AMOUNT_OF_D_DAY_DISCOUNT;

import christmas.promotion.collborator.calendar.benefit.BenefitAmount;
import christmas.promotion.collborator.calendar.benefit.DDayBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.GiveawayBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.SpecialBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.WeekdayBenefitWrapper;
import christmas.promotion.collborator.calendar.benefit.WeekendBenefitWrapper;
import christmas.promotion.collborator.generic.Won;
import christmas.promotion.collborator.menu.Menu;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.exception.InvalidReservationOrderException;
import java.util.List;
import java.util.Optional;

public class Promotions {

    private final Date today;
    private final Orders orders;
    private final Won totalPaymentAmount;

    protected Promotions(Date todayDate, Orders orders) {
        this.today = todayDate;
        this.orders = orders;
        this.totalPaymentAmount = orders.calculateTotalPrice();
    }

    public BenefitAmount askBenefitAmount() {
        if (totalPaymentAmount.intValue() < CRITERIA_AMOUNT_FOR_EVENT.get()) {
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

    private Optional<Won> calculateGiveAwayAmount() {
        if (totalPaymentAmount.intValue() < CRITERIA_AMOUNT_FOR_GIVEAWAY.get()) {
            return Optional.empty();
        }
        return Optional.of(Won.of(Menu.findBy("샴페인")
                .orElseThrow(InvalidReservationOrderException::new)
                .getPrice()));
    }

    private Optional<Won> calculateDDayAmount() {
        if (!today.contains(Promotion.D_DAY)) {
            return Optional.empty();
        }
        final int dayOfIncrease = today.date() - 1;
        return Optional.of(Won.of(
                DEFAULT_AMOUNT_OF_D_DAY_DISCOUNT.get() + (dayOfIncrease * INCREASE_AMOUNT_OF_D_DAY_DISCOUNT.get())));
    }

    private Optional<Won> calculateWeekendAmount() {
        if (!today.contains(Promotion.WEEKEND)) {
            return Optional.empty();
        }
        return Optional.of(Won.of(
                orders.countMainOrders() * AMOUNT_OF_WEEK_DISCOUNT.get()));
    }

    private Optional<Won> calculateWeekdayAmount() {
        if (!today.contains(Promotion.WEEKDAY)) {
            return Optional.empty();
        }
        return Optional.of(Won.of(
                orders.countDessertOrders() * AMOUNT_OF_WEEK_DISCOUNT.get()));
    }

    private Optional<Won> calculateSpecialAmount() {
        if (!today.contains(Promotion.SPECIAL)) {
            return Optional.empty();
        }
        return Optional.of(Won.of(AMOUNT_OF_SPECIAL_DISCOUNT.get()));
    }

}
