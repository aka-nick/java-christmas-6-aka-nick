package christmas.promotion.collborator.calendar;

public enum PromotionsAmount {

    CRITERIA_AMOUNT_FOR_EVENT(10_000),
    CRITERIA_AMOUNT_FOR_GIVEAWAY(120_000),
    DEFAULT_AMOUNT_OF_D_DAY_DISCOUNT(1_000),
    INCREASE_AMOUNT_OF_D_DAY_DISCOUNT(100),
    AMOUNT_OF_WEEK_DISCOUNT(2_023),
    AMOUNT_OF_SPECIAL_DISCOUNT(1_000),
    ;

    private final int amount;

    PromotionsAmount(int amount) {
        this.amount = amount;
    }

    public int get() {
        return amount;
    }
}
