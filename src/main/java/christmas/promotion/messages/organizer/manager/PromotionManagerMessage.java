package christmas.promotion.messages.organizer.manager;

import christmas.promotion.collborator.calendar.PromotionBadge;
import christmas.promotion.collborator.generic.Won;
import christmas.promotion.messages.GlobalMessage;
import java.util.List;

public enum PromotionManagerMessage {

    ANNOUNCE_PREVIEW_BEFORE("12월 "),
    ANNOUNCE_PREVIEW_AFTER("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    TITLE_ORDER_FOOD_NAME("<주문 메뉴>"),
    TITLE_AMOUNT_OF_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    TITLE_GIVEAWAY("<증정 메뉴>"),
    TITLE_BENEFITS("<혜택 내역>"),
    TITLE_AMOUNT_OF_BENEFITS("<총혜택 금액>"),
    MINUS_AMOUNT_OF_BENEFITS("-"),
    TITLE_AMOUNT_OF_EXPECTED("<할인 후 예상 결제 금액>"),
    TITLE_PROMOTION_BADGE("<12월 이벤트 배지>"),
    ;

    private final String message;

    PromotionManagerMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    public static String getPreviewMessage(int date) {
        return ANNOUNCE_PREVIEW_BEFORE.get() + date + ANNOUNCE_PREVIEW_AFTER.get()
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getOrderedMenuMessage(List<String> orderedMenus) {
        return TITLE_ORDER_FOOD_NAME.get() + GlobalMessage.NEW_LINE.get()
                + String.join(GlobalMessage.NEW_LINE.get(), orderedMenus)
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getTotalPriceMessage(Won totalPrice) {
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

    public static String getTotalAmountOfApplyPromotionMessage(Won amountOfTotal) {
        return TITLE_AMOUNT_OF_BENEFITS.get() + GlobalMessage.NEW_LINE.get()
                + MINUS_AMOUNT_OF_BENEFITS + amountOfTotal
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getFinalAmountMessage(Won totalOrderPrice, Won totalBenefitAmount) {
        return TITLE_AMOUNT_OF_EXPECTED.get() + GlobalMessage.NEW_LINE.get()
                + totalOrderPrice.minus(totalBenefitAmount)
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    public static String getPromotionBadgeMessage(Won totalBenefitAmount) {
        return TITLE_PROMOTION_BADGE.get() + GlobalMessage.NEW_LINE.get()
                + PromotionBadge.findPromotionBadgeBy(totalBenefitAmount)
                + GlobalMessage.BLANK_AND_NEW_LINE.get();
    }

    @Override
    public String toString() {
        return message;
    }
}
