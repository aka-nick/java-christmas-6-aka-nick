package christmas.promotion.generic.promotioncalendar;

import java.util.List;

public record Date(int date, Day day, List<Promotion> promotions) {

}
