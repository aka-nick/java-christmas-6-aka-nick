package christmas.promotion.collborator.order;

import java.util.List;

public record Orders(List<Order> orders) {

    public List<String> findAllOrderedFood() {
        return orders().stream()
                .map(Order::foodName)
                .toList();
    }

    public int calculateTotalPrice() {
        return orders().stream()
                .mapToInt(Order::foodPrice)
                .sum();
    }

}
