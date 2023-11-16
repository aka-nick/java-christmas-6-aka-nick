package christmas.promotion.enums.collaborator.promotions.benefit;

public enum WeekendBenefitWrapperMessage {

    WEEKEND_BENEFIT_MESSAGE("주말 할인"),
    ;

    private final String message;

    WeekendBenefitWrapperMessage(String message) {
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
