package christmas.promotion.messages;

public enum GlobalMessage {

    NEW_LINE(System.lineSeparator()),
    BLANK_AND_NEW_LINE(System.lineSeparator().repeat(2)),
    EXCEPTION_HEADER("[ERROR] "),
    ;

    private final String message;

    GlobalMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

}
