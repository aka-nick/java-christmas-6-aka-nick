package christmas.promotion.organizer.manager;

import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getBenefitsMessage;
import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getFinalAmountMessage;
import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getGiveawayHistoryMessage;
import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getOrderedMenuMessage;
import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getPreviewMessage;
import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getPromotionBadgeMessage;
import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getTotalAmountOfApplyPromotionMessage;
import static christmas.promotion.messages.organizer.manager.PromotionManagerMessage.getTotalPriceMessage;

import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.calendar.Promotions;
import christmas.promotion.collborator.calendar.benefit.BenefitAmount;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.organizer.io.Output;

public class PromotionManager {

    private final Output output;

    public PromotionManager(Output output) {
        this.output = output;
    }

    public void announceBenefitPreview(Date reservationDate, Orders orders) {
        BenefitAmount benefits = getBenefitAmount(reservationDate, orders);

        output.println(getPreviewMessage(reservationDate.date())
                + getOrderedMenuMessage(orders.findAllOrderedMenu())
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
