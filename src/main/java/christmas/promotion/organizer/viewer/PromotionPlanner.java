package christmas.promotion.organizer.viewer;

import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.ANNOUNCE_PREVIEW_AFTER;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.ANNOUNCE_PREVIEW_BEFORE;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.MINUS_AMOUNT_OF_BENEFITS;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.TITLE_AMOUNT_OF_BEFORE_DISCOUNT;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.TITLE_AMOUNT_OF_BENEFITS;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.TITLE_AMOUNT_OF_EXPECTED;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.TITLE_BENEFITS;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.TITLE_GIVEAWAY;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.TITLE_ORDER_FOOD_NAME;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.TITLE_PROMOTION_BADGE;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.WON_AMOUNT_OF_BENEFITS;

import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.calendar.PromotionBadge;
import christmas.promotion.collborator.calendar.Promotions;
import christmas.promotion.collborator.calendar.benefit.BenefitAmount;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.enums.GlobalMessage;
import christmas.promotion.organizer.io.Output;

public class PromotionPlanner {

    private final Output output;

    public PromotionPlanner(Output output) {
        this.output = output;
    }

    public void announceBenefitPreview(Date reservationDate, Orders orders) {
        BenefitAmount benefits = getBenefitAmount(reservationDate, orders);
        output.println(new StringBuilder()
                .append(announcePreview(reservationDate))
                .append(findOrderedFoodsNameFrom(orders))
                .append(calculateTotalPriceFrom(orders))
                .append(showGiveawayHistoryFrom(benefits))
                .append(showBenefitsFrom(benefits))
                .append(calculateTotalAmountOfApplyPromotionFrom(benefits))
                .append(calculateFinalAmount(orders, benefits))
                .append(calculatePromotionBadge(benefits)));
    }

    private static BenefitAmount getBenefitAmount(Date reservationDate, Orders orders) {
        Promotions promotionsOfReservationDate = Calendar.findPromotionsBy(reservationDate, orders);
        return promotionsOfReservationDate.askBenefitAmount();
    }

    private StringBuilder announcePreview(Date reservationDate) {
        return new StringBuilder()
                .append(ANNOUNCE_PREVIEW_BEFORE).append(reservationDate.date()).append(ANNOUNCE_PREVIEW_AFTER)
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder findOrderedFoodsNameFrom(Orders orders) {
        return new StringBuilder()
                .append(TITLE_ORDER_FOOD_NAME).append(GlobalMessage.NEW_LINE.get())
                .append(String.join(GlobalMessage.NEW_LINE.get(), orders.findAllOrderedFood()))
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculateTotalPriceFrom(Orders orders) {
        return new StringBuilder()
                .append(TITLE_AMOUNT_OF_BEFORE_DISCOUNT).append(GlobalMessage.NEW_LINE.get())
                .append(orders.calculateTotalPrice())
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder showGiveawayHistoryFrom(BenefitAmount benefits) {
        return new StringBuilder()
                .append(TITLE_GIVEAWAY).append(GlobalMessage.NEW_LINE.get())
                .append(benefits.askResultOfGiveaway())
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder showBenefitsFrom(BenefitAmount benefits) {
        StringBuilder message = new StringBuilder();
        message.append(TITLE_BENEFITS).append(GlobalMessage.NEW_LINE.get());

        return message.append(benefits.findBenefitMessages())
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculateTotalAmountOfApplyPromotionFrom(BenefitAmount benefits) {
        return new StringBuilder()
                .append(TITLE_AMOUNT_OF_BENEFITS).append(GlobalMessage.NEW_LINE.get())
                .append(MINUS_AMOUNT_OF_BENEFITS).append(benefits.amountOfTotalBenefits()).append(WON_AMOUNT_OF_BENEFITS)
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculateFinalAmount(Orders orders, BenefitAmount benefits) {
        return new StringBuilder()
                .append(TITLE_AMOUNT_OF_EXPECTED)
                .append(orders.calculateTotalPrice() - benefits.amountOfTotalBenefits()).append("원")
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculatePromotionBadge(BenefitAmount benefits) {
        return new StringBuilder()
                .append(TITLE_PROMOTION_BADGE)
                .append(PromotionBadge.findPromotionBadgeBy(benefits.amountOfTotalBenefits()))
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

}
