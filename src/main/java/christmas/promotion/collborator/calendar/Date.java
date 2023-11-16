package christmas.promotion.collborator.calendar;

import christmas.promotion.exception.InvalidReservationDateException;
import java.util.List;

public record Date(int date, Day day, List<Promotion> promotions) {

    public boolean contains(Promotion promotion) {
        return this.promotions.contains(promotion);
    }

    public static Date of(int date) {
        return Calendar.findDate(date)
                .orElseThrow(InvalidReservationDateException::new);
    }

}
