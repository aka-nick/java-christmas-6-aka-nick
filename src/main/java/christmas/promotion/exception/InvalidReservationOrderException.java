package christmas.promotion.exception;

import static christmas.promotion.enums.exception.InvalidReservationOrderExceptionMessage.EXCEPTION_INVALID_ORDER;

public class InvalidReservationOrderException extends IllegalArgumentException{

    public InvalidReservationOrderException() {
        super(EXCEPTION_INVALID_ORDER.get());
    }

    public InvalidReservationOrderException(Throwable cause) {
        super(EXCEPTION_INVALID_ORDER.get(), cause);
    }

}
