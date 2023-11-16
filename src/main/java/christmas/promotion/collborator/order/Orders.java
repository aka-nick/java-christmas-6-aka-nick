package christmas.promotion.collborator.order;

import static christmas.promotion.enums.collaborator.order.OrdersMessage.SEPARATOR;
import static christmas.promotion.enums.collaborator.order.OrdersMessage.UNIT_FOOD_NAME;
import static christmas.promotion.enums.collaborator.order.OrdersQuantity.QUANTITY_LIMIT_ORDERED_AT_ONCE;

import christmas.promotion.collborator.generic.Won;
import christmas.promotion.exception.InvalidReservationOrderException;
import java.util.List;

public record Orders(List<Order> orders) {

    public Orders {
        validate(orders);
    }

    private void validate(List<Order> orders) {
        if (isAllBeverage(orders)
                || isDuplicated(orders)
                || isUnacceptableNumberOfOrders(orders)) {
            throw new InvalidReservationOrderException();
        }
    }

    public boolean isAllBeverage(List<Order> orders) {
        return orders.stream()
                .allMatch(Order::isBeverage);
    }

    public boolean isDuplicated(List<Order> orders) {
        return orders.size() != orders.stream()
                .map(Order::foodName)
                .distinct()
                .count();
    }

    public boolean isUnacceptableNumberOfOrders(List<Order> orders) {
        return QUANTITY_LIMIT_ORDERED_AT_ONCE.get() < orders.stream()
                .mapToInt(Order::foodQuantity)
                .sum();
    }

    public List<String> findAllOrderedMenu() {
        return orders().stream()
                .map(Orders::makeMessageOfOrderedMenu)
                .toList();
    }

    private static String makeMessageOfOrderedMenu(Order order) {
        return order.foodName() + SEPARATOR + order.foodQuantity() + UNIT_FOOD_NAME;
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
