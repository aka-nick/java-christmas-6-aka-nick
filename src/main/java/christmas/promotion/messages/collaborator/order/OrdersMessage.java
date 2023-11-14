package christmas.promotion.messages.collaborator.order;

public enum OrdersMessage {

    SEPARATOR(" "),
    UNIT_FOOD_NAME("개"),
    ;

    private final String message;

    OrdersMessage(String message) {
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
