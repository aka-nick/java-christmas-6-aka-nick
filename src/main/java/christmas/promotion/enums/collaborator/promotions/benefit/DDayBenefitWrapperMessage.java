package christmas.promotion.enums.collaborator.promotions.benefit;

public enum DDayBenefitWrapperMessage {

    D_DAY_BENEFIT_MESSAGE("크리스마스 디데이 할인"),
    ;

    private final String message;

    DDayBenefitWrapperMessage(String message) {
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
