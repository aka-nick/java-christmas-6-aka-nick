package christmas.promotion.messages.collaborator.calendar.benefit;

public enum BenefitWrapperMessage {

    SEPARATOR(": "),
    MINUS("-"),
    ;

    private final String message;

    BenefitWrapperMessage(String message) {
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
