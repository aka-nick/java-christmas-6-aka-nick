package christmas.promotion.messages.io;

public enum ConsoleInputMessage {

    EXCEPTION_WRONG_INPUT_VALUE("잘못된 입력값입니다."),
    EXCEPTION_WRONG_SPLIT_REGEX("적절하지 않은 구분 정규식"),
    ;

    private final String message;

    ConsoleInputMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

}
