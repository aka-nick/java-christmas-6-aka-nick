package christmas.promotion.collborator.shop;

import christmas.promotion.collborator.generic.Money;

public class Food {

    private final String name;
    private final Money price;

    public Food(String name, long amount) {
        this.name = name;
        this.price = Money.won(amount);
    }

}
