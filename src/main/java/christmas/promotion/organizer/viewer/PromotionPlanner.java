package christmas.promotion.organizer.viewer;

import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getBenefitsMessage;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getFinalAmountMessage;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getGiveawayHistoryMessage;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getOrderFoodNameMessage;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getPreviewMessage;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getPromotionBadgeMessage;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getTotalAmountOfApplyPromotionMessage;
import static christmas.promotion.enums.organizer.viewer.PromotionPlannerMessage.getTotalPriceMessage;

import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.calendar.Promotions;
import christmas.promotion.collborator.calendar.benefit.BenefitAmount;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.organizer.io.Output;

public class PromotionPlanner {

    private final Output output;

    public PromotionPlanner(Output output) {
        this.output = output;
    }

    public void announceBenefitPreview(Date reservationDate, Orders orders) {
        BenefitAmount benefits = getBenefitAmount(reservationDate, orders);

        output.println(getPreviewMessage(reservationDate.date())
                + getOrderFoodNameMessage(orders.findAllOrderedFood())
                + getTotalPriceMessage(orders.calculateTotalPrice())
                + getGiveawayHistoryMessage(benefits.askResultOfGiveaway())
                + getBenefitsMessage(benefits.findBenefitMessages())
                + getTotalAmountOfApplyPromotionMessage(benefits.amountOfTotalBenefits())
                + getFinalAmountMessage(orders.calculateTotalPrice(), benefits.amountOfTotalBenefits())
                + getPromotionBadgeMessage(benefits.amountOfTotalBenefits()));
    }

    private static BenefitAmount getBenefitAmount(Date reservationDate, Orders orders) {
        Promotions promotionsOfReservationDate = Calendar.findPromotionsBy(reservationDate, orders);
        return promotionsOfReservationDate.askBenefitAmount();
    }

}
