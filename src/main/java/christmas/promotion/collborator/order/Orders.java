package christmas.promotion.collborator.order;

import static christmas.promotion.messages.collaborator.order.OrdersMessage.SEPARATOR;
import static christmas.promotion.messages.collaborator.order.OrdersMessage.UNIT_FOOD_NAME;

import java.util.List;

public record Orders(List<Order> orders) {

    public List<String> findAllOrderedMenu() {
        return orders().stream()
                .map(order -> order.foodName() + SEPARATOR + order.foodQuantity() + UNIT_FOOD_NAME)
                .toList();
    }

    public int calculateTotalPrice() {
        return orders().stream()
                .mapToInt(Order::foodPrice)
                .sum();
    }

    public int countMainOrders() {
        return (int) orders().stream()
                .filter(Order::isMain)
                .count();
    }

    public int countDessertOrders() {
        return (int) orders().stream()
                .filter(Order::isDessert)
                .count();
    }

    public int countTotalMenu() {
        return orders.stream()
                .mapToInt(Order::foodQuantity)
                .sum();
    }

    public boolean isAllBeverage() {
        return orders().stream()
                .allMatch(Order::isBeverage);
    }

}
