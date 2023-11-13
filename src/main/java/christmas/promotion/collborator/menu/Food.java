package christmas.promotion.collborator.menu;

import java.util.Objects;

public class Food {

    enum Category { APPETIZER, MAIN, DESSERT, BEVERAGE }

    private final Category category;
    private final String name;
    private final int price; // TODO : 추후 Money 타입으로 교체

    public Food(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isMain() {
        return category == Category.MAIN;
    }

    public boolean isDessert() {
        return category == Category.DESSERT;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
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
