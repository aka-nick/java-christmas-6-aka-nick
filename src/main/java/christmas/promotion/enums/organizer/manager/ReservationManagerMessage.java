package christmas.promotion.enums.organizer.manager;

public enum ReservationManagerMessage {

    SAY_GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_RESERVATION_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    TAKE_ORDERS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EXCEPTION_INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    EXCEPTION_INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ReservationManagerMessage(String message) {
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
