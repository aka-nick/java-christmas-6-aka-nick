package christmas.promotion.organizer.viewer;

import static christmas.promotion.enums.organizer.viewer.ReservationManagerMessage.*;

import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.OrderFood;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.exception.InvalidReservationDateException;
import christmas.promotion.exception.InvalidReservationOrderException;
import christmas.promotion.organizer.calendar.ReservationDate;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.InteractionRepeatable;
import christmas.promotion.organizer.io.Output;
import java.util.List;

public class ReservationManager implements InteractionRepeatable {

    private final Input input;
    private final Output output;

    public ReservationManager(Input input, Output output) {
        this.input = input;
        this.output = output;
    }


    public void sayGreet() {
        output.println(SAY_GREET);
    }

    public Date askReservationDate() {
        final Integer reservationDate = supplyInteraction(() -> {
            output.println(ASK_RESERVATION_DATE);
            try {
                return input.number();
            } catch (IllegalArgumentException e) {
                throw new InvalidReservationDateException(e);
            }
        });

        return Calendar.findDate(reservationDate);
    }

    public Orders takeOrders() {
        return supplyInteraction(() -> {
            try {
                output.println(TAKE_ORDERS);
                List<String> reservations = input.strings(",");
                return new Orders(reservations.stream()
                        .map(reservation -> reservation.split("-"))
                        .map(foodElements -> Order.place(convertToOrderFood(foodElements)))
                        .toList());
            } catch (IllegalArgumentException e) {
                throw new InvalidReservationOrderException(e);
            }
        });
    }

    private OrderFood convertToOrderFood(String[] fromElement) {
        try {
            return new OrderFood(fromElement[0], Integer.parseInt(fromElement[1]));
        } catch (NumberFormatException e) {
            throw new InvalidReservationOrderException(e);
        }
    }

}
