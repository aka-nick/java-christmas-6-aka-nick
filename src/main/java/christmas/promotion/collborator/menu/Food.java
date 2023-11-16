package christmas.promotion.collborator.menu;

import christmas.promotion.collborator.generic.Won;
import java.util.Objects;

public class Food {

    enum Category { APPETIZER, MAIN, DESSERT, BEVERAGE;}

    private final Category category;
    private final String name;
    private final Won price;

    public Food(Category category, String name, Won price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public boolean isMain() {
        return category == Category.MAIN;
    }

    public boolean isDessert() {
        return category == Category.DESSERT;
    }

    public boolean isBeverage() {
        return category == Category.BEVERAGE;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
}
