package christmas.promotion.organizer.manager;

import static christmas.promotion.messages.organizer.manager.OrderManagerMessage.ORDER_DELIMITER;
import static christmas.promotion.messages.organizer.manager.OrderManagerMessage.ORDER_FOODNAME_AND_QUANTITY_SEPARATOR;
import static christmas.promotion.messages.organizer.manager.ReservationManagerMessage.TAKE_ORDERS;

import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.exception.InvalidReservationOrderException;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.InteractionRepeatable;
import christmas.promotion.organizer.io.Output;

public class OrderManager implements InteractionRepeatable {

    private final Input input;
    private final Output output;

    public OrderManager(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public Orders takeOrders() {
        return supplyInteractionWithCustomException(() -> {
            output.println(TAKE_ORDERS);
            return new Orders(input.strings(ORDER_DELIMITER.get()).stream()
                    .map(reservation -> reservation.split(ORDER_FOODNAME_AND_QUANTITY_SEPARATOR.get()))
                    .map(foodElements -> Order.place(foodElements[0], Integer.parseInt(foodElements[1])))
                    .toList());
        }, new InvalidReservationOrderException());
    }

}
