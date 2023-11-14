package christmas.promotion.messages.exception;

public enum InvalidReservationOrderExceptionMessage {

    EXCEPTION_INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    InvalidReservationOrderExceptionMessage(String message) {
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
