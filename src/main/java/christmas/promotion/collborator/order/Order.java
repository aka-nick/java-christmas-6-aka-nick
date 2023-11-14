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

    public static Order place(String foodName, int foodQuantity) {
        return new Order(
                Menu.findBy(foodName).orElseThrow(InvalidReservationOrderException::new),
                passValidationOrThrow(foodQuantity));
    }

    private static int passValidationOrThrow(int foodQuantity) {
        if (foodQuantity <= 0 || 20 < foodQuantity) {
            throw new InvalidReservationOrderException();
        }
        return foodQuantity;
    }

    public String foodName() {
        return food.getName();
    }

    public int foodQuantity() {
        return quantity;
    }

    public int foodPrice() {
        return food.getPrice() * foodQuantity();
    }

    public boolean isMain() {
        return food.isMain();
    }

    public boolean isDessert() {
        return food.isDessert();
    }

    public boolean isBeverage() {
        return food.isBeverage();
    }

}
