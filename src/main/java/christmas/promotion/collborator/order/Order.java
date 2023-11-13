package christmas.promotion.collborator.order;

import christmas.promotion.collborator.menu.Food;
import christmas.promotion.collborator.menu.Menu;
import christmas.promotion.exception.InvalidReservationOrderException;

public class Order {

    private final Food food;
    private final int quantity;

    private Order(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public static Order place(String foodName, int foodPrice) {
        return new Order(
                Menu.findBy(foodName).orElseThrow(InvalidReservationOrderException::new),
                foodPrice);
    }

    public String foodName() {
        return food.getName();
    }

    public int foodPrice() {
        return food.getPrice() * quantity;
    }

    public boolean isMain() {
        return food.isMain();
    }

    public boolean isDessert() {
        return food.isDessert();
    }

}
