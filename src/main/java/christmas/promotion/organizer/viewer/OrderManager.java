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

            // TODO : 밸리데이션 로직 메서드 추출 및 중복 코드 제거
            if (new Orders(reservations.stream()
                    .map(reservation -> reservation.split("-"))
                    .map(foodElements -> Order.place(foodElements[0], Integer.parseInt(foodElements[1])))
                    .toList()).isAllBeverage()) {
                throw new InvalidReservationOrderException();
            }

            return new Orders(reservations.stream()
                    .map(reservation -> reservation.split("-"))
                    .map(foodElements ->
                            Order.place(foodElements[0], Integer.parseInt(foodElements[1])))
                    .toList());
        }, new InvalidReservationOrderException());
    }

}
