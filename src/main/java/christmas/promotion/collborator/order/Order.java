package christmas.promotion.collborator.order;

import christmas.promotion.collborator.menu.Food;
import christmas.promotion.collborator.menu.Menu;

public class Order {

    private final Food food;
    private final int amount;

    private Order(Food food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    public static Order place(String[] foodElements) {
        // TODO : 컨버트 로직 같은건 나중에 전용뷰가 생기면 그리로 옮겨져야 한다, 아니면 전용 매퍼를 만들던지
        return new Order(Menu.findBy(foodElements[0]), Integer.parseInt(foodElements[1]));
    }

    public Food getFood() {
        return food;
    }

    public int getAmount() {
        return amount;
    }

}
