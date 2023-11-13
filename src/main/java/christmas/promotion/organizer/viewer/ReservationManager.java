package christmas.promotion.organizer.viewer;

import static christmas.promotion.enums.organizer.viewer.ReservationManagerMessage.*;

import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.exception.InvalidReservationDateException;
import christmas.promotion.organizer.calendar.ReservationDate;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.InteractionRepeatable;
import christmas.promotion.organizer.io.Output;

public class ReservationManager implements InteractionRepeatable {

    private final Input input;
    private final Output output;

    public ReservationManager(Input input, Output output) {
        this.input = input;
        this.output = output;
    }


    public void greetToGuest() {
        output.println();
    }

    public Date askReservationDate() {
        final Integer reservationDate = supplyInteraction(() ->
        {
            output.println(ASK_RESERVATION_DATE);
            try {
                return input.number();
            } catch (IllegalArgumentException e) {
                throw new InvalidReservationDateException(e);
            }
        });

        return Calendar.findDate(reservationDate);
    }

}
