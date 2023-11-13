package christmas.promotion.collborator.calendar;

import christmas.promotion.collborator.order.Orders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Calendar {

    private static final Map<Integer, Date> calendar = new HashMap<>();

    private Calendar() {
    }

    static {
        calendar.put(1, new Date(1, Day.FRIDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(2, new Date(2, Day.SATURDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(3, new Date(3, Day.SUNDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY, Promotion.SPECIAL)));
        calendar.put(4, new Date(4, Day.MONDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(5, new Date(5, Day.TUESDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(6, new Date(6, Day.WEDNESDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(7, new Date(7, Day.THURSDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(8, new Date(8, Day.FRIDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(9, new Date(9, Day.SATURDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(10, new Date(10, Day.SUNDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY, Promotion.SPECIAL)));
        calendar.put(11, new Date(11, Day.MONDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(12, new Date(12, Day.TUESDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(13, new Date(13, Day.WEDNESDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(14, new Date(14, Day.THURSDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(15, new Date(15, Day.FRIDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(16, new Date(16, Day.SATURDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(17, new Date(17, Day.SUNDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY, Promotion.SPECIAL)));
        calendar.put(18, new Date(18, Day.MONDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(19, new Date(19, Day.TUESDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(20, new Date(20, Day.WEDNESDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(21, new Date(21, Day.THURSDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY)));
        calendar.put(22, new Date(22, Day.FRIDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(23, new Date(23, Day.SATURDAY, List.of(Promotion.D_DAY, Promotion.WEEKEND)));
        calendar.put(24, new Date(24, Day.SUNDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY, Promotion.SPECIAL)));
        calendar.put(25, new Date(25, Day.MONDAY, List.of(Promotion.D_DAY, Promotion.WEEKDAY, Promotion.SPECIAL)));
        calendar.put(26, new Date(26, Day.TUESDAY, List.of(Promotion.WEEKDAY)));
        calendar.put(27, new Date(27, Day.WEDNESDAY, List.of(Promotion.WEEKDAY)));
        calendar.put(28, new Date(28, Day.THURSDAY, List.of(Promotion.WEEKDAY)));
        calendar.put(29, new Date(29, Day.FRIDAY, List.of(Promotion.WEEKEND)));
        calendar.put(30, new Date(30, Day.SATURDAY, List.of(Promotion.WEEKEND)));
        calendar.put(31, new Date(31, Day.SUNDAY, List.of(Promotion.WEEKDAY, Promotion.SPECIAL)));
    }

    public static Optional<Date> findDate(int numberOfReservationDate) {
        try {
            return Optional.ofNullable(calendar.get(numberOfReservationDate));
        } catch (ClassCastException | IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.", e);
        }
    }

    public static Promotions findPromotionsBy(Date reservationDate, Orders orders) {
        try {
            return new Promotions(reservationDate, orders);
        } catch (ClassCastException | IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.", e);
        }
    }

}
