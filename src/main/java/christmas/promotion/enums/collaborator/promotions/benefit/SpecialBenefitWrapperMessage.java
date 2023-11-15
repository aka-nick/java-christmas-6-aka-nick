package christmas.promotion.enums.collaborator.promotions.benefit;

public enum SpecialBenefitWrapperMessage {

    SPECIAL_BENEFIT_MESSAGE("특별 할인"),
    ;

    private final String message;

    SpecialBenefitWrapperMessage(String message) {
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
