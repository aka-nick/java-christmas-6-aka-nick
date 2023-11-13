package christmas.promotion.exception;

import static christmas.promotion.enums.exception.InvalidReservationDateExceptionMessage.EXCEPTION_INVALID_DATE;

import christmas.promotion.enums.GlobalMessage;

public class InvalidReservationOrderException extends IllegalArgumentException{

    public InvalidReservationOrderException(Throwable cause) {
        super(GlobalMessage.EXCEPTION_HEADER.get() + EXCEPTION_INVALID_DATE, cause);
    }

}
