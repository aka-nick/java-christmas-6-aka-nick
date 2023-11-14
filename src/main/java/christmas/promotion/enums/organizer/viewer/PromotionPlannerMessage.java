package christmas.promotion.enums.organizer.viewer;

import christmas.promotion.collborator.calendar.PromotionBadge;
import christmas.promotion.enums.GlobalMessage;
import java.util.List;

public enum PromotionPlannerMessage {

    ANNOUNCE_PREVIEW_BEFORE("12월 "),
    ANNOUNCE_PREVIEW_AFTER("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    TITLE_ORDER_FOOD_NAME("<주문 메뉴>"),
    TITLE_AMOUNT_OF_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    TITLE_GIVEAWAY("<증정 메뉴>"),
    TITLE_BENEFITS("<혜택 내역>"),
    TITLE_AMOUNT_OF_BENEFITS("<총혜택 금액>"),
    MINUS_AMOUNT_OF_BENEFITS("-"),
    WON_AMOUNT_OF_BENEFITS("원"),
    TITLE_AMOUNT_OF_EXPECTED("<할인 후 예상 결제 금액>"),
    TITLE_PROMOTION_BADGE("<12월 이벤트 배지>"),
    ;

    private final String message;

    PromotionPlannerMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    public static String getPreviewMessage(int date) {
        return ANNOUNCE_PREVIEW_BEFORE.get() + date + ANNOUNCE_PREVIEW_AFTER.get()
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getOrderFoodNameMessage(List<String> orderedFood) {
        return TITLE_ORDER_FOOD_NAME.get() + GlobalMessage.NEW_LINE.get()
                + String.join(GlobalMessage.NEW_LINE.get(), orderedFood)
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getTotalPriceMessage(int totalPrice) {
        return TITLE_AMOUNT_OF_BEFORE_DISCOUNT.get() + GlobalMessage.NEW_LINE.get()
                + totalPrice
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getGiveawayHistoryMessage(String benefitsHistory) {
        return TITLE_GIVEAWAY.get() + GlobalMessage.NEW_LINE.get()
                + benefitsHistory
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getBenefitsMessage(String benefitsMessage) {
        return TITLE_BENEFITS.get() + GlobalMessage.NEW_LINE.get()
                + benefitsMessage
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getTotalAmountOfApplyPromotionMessage(Integer amountOfTotal) {
        return TITLE_AMOUNT_OF_BENEFITS.get() + GlobalMessage.NEW_LINE.get()
                + MINUS_AMOUNT_OF_BENEFITS + amountOfTotal + WON_AMOUNT_OF_BENEFITS.get()
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getFinalAmountMessage(int totalOrderPrice, int totalBenefitAmount) {
        return TITLE_AMOUNT_OF_EXPECTED.get()
                + (totalOrderPrice - totalBenefitAmount) + WON_AMOUNT_OF_BENEFITS.get()
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getPromotionBadgeMessage(int totalBenefitAmount) {
        return TITLE_PROMOTION_BADGE.get()
                + PromotionBadge.findPromotionBadgeBy(totalBenefitAmount)
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    @Override
    public String toString() {
        return message;
    }
}
