package christmas.promotion.enums.collaborator.promotions.benefit;

public enum WeekdayBenefitWrapperMessage {

    WEEKDAY_BENEFIT_MESSAGE("평일 할인"),
    ;

    private final String message;

    WeekdayBenefitWrapperMessage(String message) {
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
