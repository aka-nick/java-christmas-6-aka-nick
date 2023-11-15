package christmas.promotion.enums.collaborator.generic;

public enum WonMessage {

    UNIT_OF_WON("원"),
    ;

    private final String message;

    WonMessage(String message) {
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
