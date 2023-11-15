package christmas.promotion.enums.collaborator.promotions.benefit;

public enum GiveawayBenefitWrapperMessage {

    GIVEAWAY_BENEFIT_MESSAGE("증정 이벤트"),
    ;

    private final String message;

    GiveawayBenefitWrapperMessage(String message) {
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
