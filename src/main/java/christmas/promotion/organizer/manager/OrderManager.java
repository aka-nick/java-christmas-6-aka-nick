package christmas.promotion.organizer.manager;

import static christmas.promotion.enums.organizer.manager.OrderManagerMessage.ORDER_DELIMITER;
import static christmas.promotion.enums.organizer.manager.OrderManagerMessage.ORDER_FOODNAME_AND_QUANTITY_SEPARATOR;
import static christmas.promotion.enums.organizer.manager.ReservationManagerMessage.TAKE_ORDERS;

import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.exception.InvalidReservationOrderException;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.InteractionRepeatable;
import christmas.promotion.organizer.io.Output;
import java.util.List;
import java.util.function.Function;

public class OrderManager implements InteractionRepeatable {

    private final Input input;
    private final Output output;

    public OrderManager(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public Orders takeOrders() {
        return supplyInteractionWithException(() -> {
            output.println(TAKE_ORDERS);
            return new Orders(receiveCustomerOrderRequests().stream()
                    .map(convertToFoodNameAndQuantity())
                    .map(placeOrderByFoodNameAndQuantity())
                    .toList());
        }, new InvalidReservationOrderException());
    }

    private List<String> receiveCustomerOrderRequests() {
        return input.strings(ORDER_DELIMITER.get());
    }

    private static Function<String, String[]> convertToFoodNameAndQuantity() {
        return reservation -> reservation.split(ORDER_FOODNAME_AND_QUANTITY_SEPARATOR.get());
    }

    private static Function<String[], Order> placeOrderByFoodNameAndQuantity() {
        return foodElements -> Order.place(foodElements[0], Integer.parseInt(foodElements[1]));
    }

}
