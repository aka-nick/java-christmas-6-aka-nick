package christmas.promotion.organizer.viewer;

import static christmas.promotion.messages.organizer.viewer.ReservationManagerMessage.TAKE_ORDERS;

import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.exception.InvalidReservationOrderException;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.InteractionRepeatable;
import christmas.promotion.organizer.io.Output;
import java.util.List;

public class OrderManager implements InteractionRepeatable {

    public static final int QUANTITY_LIMIT_ORDERED_AT_ONCE = 20;
    private final Input input;
    private final Output output;

    public OrderManager(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public Orders takeOrders() {
        return supplyInteractionWithCustomException(() -> {
            output.println(TAKE_ORDERS);
            List<String> reservations = input.strings(",");

            // TODO : 밸리데이션 로직 메서드 추출
            if (reservations.size() != reservations.stream()
                    .map(reservation -> reservation.split("-")[0])
                    .distinct()
                    .count()) {
                throw new InvalidReservationOrderException();
            }

            Orders orders = new Orders(reservations.stream()
                    .map(reservation -> reservation.split("-"))
                    .map(foodElements -> Order.place(foodElements[0], Integer.parseInt(foodElements[1])))
                    .toList());

            if (orders.isAllBeverage()) {
                throw new InvalidReservationOrderException();
            }

            if (QUANTITY_LIMIT_ORDERED_AT_ONCE < orders.countTotalMenu()) {
                throw new InvalidReservationOrderException();
            }

            return orders;
        }, new InvalidReservationOrderException());
    }

}
