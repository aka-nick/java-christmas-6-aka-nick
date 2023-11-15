package christmas.promotion.organizer.manager;

import static christmas.promotion.messages.organizer.manager.ReservationManagerMessage.ASK_RESERVATION_DATE;
import static christmas.promotion.messages.organizer.manager.ReservationManagerMessage.SAY_GREETING;

import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.exception.InvalidReservationDateException;
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

    public void sayGreeting() {
        output.println(SAY_GREETING);
    }

    public Date getReservation() {
        return supplyInteractionWithCustomException(() -> {
            output.println(ASK_RESERVATION_DATE);
            return Date.of(input.number());
        }, new InvalidReservationDateException());
    }

}
