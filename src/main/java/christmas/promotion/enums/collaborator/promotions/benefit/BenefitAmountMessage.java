package christmas.promotion.enums.collaborator.promotions.benefit;

public enum BenefitAmountMessage {

    NO_BENEFITS("없음"),
    ITEM_OF_GIVEAWAY("샴페인 1개"),
    GIVEAWAY_BENEFIT_MESSAGE("증정 이벤트"),
    ;

    private final String message;

    BenefitAmountMessage(String message) {
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
