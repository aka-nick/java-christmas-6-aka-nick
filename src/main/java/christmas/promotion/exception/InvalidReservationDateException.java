package christmas.promotion.exception;

import static christmas.promotion.enums.exception.InvalidReservationDateExceptionMessage.*;

import christmas.promotion.enums.GlobalMessage;

public class InvalidReservationDateException extends IllegalArgumentException{

    public InvalidReservationDateException(Throwable cause) {
        super(GlobalMessage.EXCEPTION_HEADER.get() + EXCEPTION_INVALID_RESERVATION_DATE, cause);
    }

}
