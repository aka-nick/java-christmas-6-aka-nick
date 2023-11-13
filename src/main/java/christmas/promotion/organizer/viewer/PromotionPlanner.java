package christmas.promotion.organizer.viewer;

import christmas.promotion.collborator.calendar.BenefitAmount;
import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.calendar.PromotionBadge;
import christmas.promotion.collborator.calendar.Promotions;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.enums.GlobalMessage;
import christmas.promotion.organizer.io.Output;
import java.util.ArrayList;
import java.util.List;

public class PromotionPlanner {

    private final Output output;

    public PromotionPlanner(Output output) {
        this.output = output;
    }

    // TODO : 더 줄일 수 있으면 줄여보자
    public void announceBenefitPreview(Date reservationDate, Orders orders) {
        BenefitAmount benefits = getBenefitAmount(reservationDate, orders);

        StringBuilder benefitPreviewMessage = new StringBuilder();
        benefitPreviewMessage
                .append(announcePreviewMent(reservationDate))
                .append(findOrderedFoodsNameFrom(orders))
                .append(findOrderedFoodsNameFrom(orders))
                .append(calculateTotalPriceFrom(orders))
                .append(showGiveawayHistoryFrom(benefits))
                .append(showDiscountHistoryFrom(benefits))
                .append(calculateTotalAmountOfApplyPromotionFrom(benefits))
                .append(calculateFinalAmount(orders, benefits))
                .append(calculatePromotionBadge(benefits));
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

    // TODO : 더 줄일 수 있으면 줄여보자
    private StringBuilder showDiscountHistoryFrom(BenefitAmount benefits) {
        StringBuilder message = new StringBuilder();
        message.append("<혜택 내역>").append(GlobalMessage.NEW_LINE.get());

        if (benefits.isAllDiscountEmpty()) {
            return message.append("없음").append(GlobalMessage.BLANK_AND_NEW_LINE.get());
        }

        List<String> benefitMessages = new ArrayList<>();
        benefits.amountOfDDay().ifPresent(amount -> benefitMessages.add("크리스마스 디데이 할인: -" + amount + "원"));
        benefits.amountOfWeekend().ifPresent(amount -> benefitMessages.add("주말 할인: -" + amount + "원"));
        benefits.amountOfWeekday().ifPresent(amount -> benefitMessages.add("평일 할인: -" + amount + "원"));
        benefits.amountOfSpecial().ifPresent(amount -> benefitMessages.add("특별 할인: -" + amount + "원"));
        benefits.amountOfGiveaway().ifPresent(amount -> benefitMessages.add("증정 이벤트: -" + amount + "원"));
        return message.append(String.join(GlobalMessage.NEW_LINE.get(), benefitMessages))
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
