package christmas.promotion.messages.collaborator.promotions;

public enum PromotionsMessage {

    GIVEAWAY_MENU_NAME("샴페인"),
    ;

    private final String message;

    PromotionsMessage(String message) {
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
