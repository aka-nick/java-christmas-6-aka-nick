package christmas.promotion.enums.collaborator.order;

public enum OrdersQuantity {

    QUANTITY_LIMIT_ORDERED_AT_ONCE(20),
    ;

    private final int quantity;

    OrdersQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int get() {
        return quantity;
    }

}
