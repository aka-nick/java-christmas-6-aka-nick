package christmas.promotion.exception;

import static christmas.promotion.messages.exception.InvalidReservationDateExceptionMessage.*;

public class InvalidReservationDateException extends IllegalArgumentException{

    public InvalidReservationDateException() {
        super(EXCEPTION_INVALID_DATE.get());
    }

    public InvalidReservationDateException(Throwable cause) {
        super(EXCEPTION_INVALID_DATE.get(), cause);
    }

}
