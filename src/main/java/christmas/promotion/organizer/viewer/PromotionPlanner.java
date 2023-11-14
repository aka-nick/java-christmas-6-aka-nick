package christmas.promotion.organizer.viewer;

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
                .append(announcePreviewMent(reservationDate))
                .append(findOrderedFoodsNameFrom(orders))
                .append(calculateTotalPriceFrom(orders))
                .append(showGiveawayHistoryFrom(benefits))
                .append(showDiscountHistoryFrom(benefits))
                .append(calculateTotalAmountOfApplyPromotionFrom(benefits))
                .append(calculateFinalAmount(orders, benefits))
                .append(calculatePromotionBadge(benefits)));
    }

    private static BenefitAmount getBenefitAmount(Date reservationDate, Orders orders) {
        Promotions promotionsOfReservationDate = Calendar.findPromotionsBy(reservationDate, orders);
        return promotionsOfReservationDate.askBenefitAmount();
    }

    private StringBuilder announcePreviewMent(Date reservationDate) {
        return new StringBuilder()
                .append("12월").append(reservationDate.date()).append("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder findOrderedFoodsNameFrom(Orders orders) {
        return new StringBuilder()
                .append("<주문 메뉴>").append(GlobalMessage.NEW_LINE.get())
                .append(String.join(GlobalMessage.NEW_LINE.get(), orders.findAllOrderedFood()))
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculateTotalPriceFrom(Orders orders) {
        return new StringBuilder()
                .append("<할인 전 총주문 금액>").append(GlobalMessage.NEW_LINE.get())
                .append(orders.calculateTotalPrice())
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder showGiveawayHistoryFrom(BenefitAmount benefits) {
        return new StringBuilder()
                .append("<증정 메뉴>").append(GlobalMessage.NEW_LINE.get())
                .append(benefits.askResultOfGiveaway())
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder showDiscountHistoryFrom(BenefitAmount benefits) {
        StringBuilder message = new StringBuilder();
        message.append("<혜택 내역>").append(GlobalMessage.NEW_LINE.get());

        return message.append(benefits.findBenefitMessages())
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculateTotalAmountOfApplyPromotionFrom(BenefitAmount benefits) {
        return new StringBuilder()
                .append("<총혜택 금액>").append(GlobalMessage.NEW_LINE.get())
                .append("-").append(benefits.amountOfTotalBenefits()).append("원")
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculateFinalAmount(Orders orders, BenefitAmount benefits) {
        return new StringBuilder()
                .append("<할인 후 예상 결제 금액>")
                .append(orders.calculateTotalPrice() - benefits.amountOfTotalBenefits()).append("원")
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

    private StringBuilder calculatePromotionBadge(BenefitAmount benefits) {
        return new StringBuilder()
                .append("<12월 이벤트 배지>")
                .append(PromotionBadge.findPromotionBadgeBy(benefits.amountOfTotalBenefits()))
                .append(GlobalMessage.BLANK_AND_NEW_LINE.get());
    }

}
