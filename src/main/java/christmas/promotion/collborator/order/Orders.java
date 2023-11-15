package christmas.promotion.collborator.order;

import static christmas.promotion.messages.collaborator.order.OrdersMessage.SEPARATOR;
import static christmas.promotion.messages.collaborator.order.OrdersMessage.UNIT_FOOD_NAME;

import christmas.promotion.collborator.generic.Won;
import christmas.promotion.exception.InvalidReservationOrderException;
import java.util.List;

public record Orders(List<Order> orders) {

    public static final int QUANTITY_LIMIT_ORDERED_AT_ONCE = 20;

    public Orders {
        validate(orders);
    }

    private void validate(List<Order> orders) {
        if (isAllBeverage(orders)
                || isDuplicate(orders)
                || isUnacceptableNumberOfOrders(orders)) {
            throw new InvalidReservationOrderException();
        }
    }

    public boolean isAllBeverage(List<Order> orders) {
        return orders.stream()
                .allMatch(Order::isBeverage);
    }

    public boolean isUnacceptableNumberOfOrders(List<Order> orders) {
        return QUANTITY_LIMIT_ORDERED_AT_ONCE < orders.stream()
                .mapToInt(Order::foodQuantity)
                .sum();
    }

    public boolean isDuplicate(List<Order> orders) {
        return orders.size() != orders.stream()
                .map(Order::foodName)
                .distinct()
                .count();
    }

    public List<String> findAllOrderedMenu() {
        return orders().stream()
                .map(order -> order.foodName() + SEPARATOR + order.foodQuantity() + UNIT_FOOD_NAME)
                .toList();
    }

    public Won calculateTotalPrice() {
        return Won.of(orders().stream()
                .mapToInt(Order::foodPrice)
                .sum());
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

}
