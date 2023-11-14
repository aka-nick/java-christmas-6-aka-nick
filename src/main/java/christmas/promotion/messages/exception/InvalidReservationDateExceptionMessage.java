package christmas.promotion.messages.exception;

public enum InvalidReservationDateExceptionMessage {

    EXCEPTION_INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    InvalidReservationDateExceptionMessage(String message) {
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
