package christmas.promotion.enums.organizer.viewer;

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

    @Override
    public String toString() {
        return message;
    }
}
