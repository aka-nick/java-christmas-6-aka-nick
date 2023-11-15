package christmas.promotion.messages.organizer.manager;

public enum OrderManagerMessage {

    ORDER_DELIMITER(","),
    ORDER_FOODNAME_AND_QUANTITY_SEPARATOR("-"),
    ;

    private final String message;

    OrderManagerMessage(String message) {
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
