package christmas.promotion.collborator.promotioncalendar;

import java.util.List;

public record Date(int date, Day day, List<Promotion> promotions) {

    public boolean contains(Promotion promotion) {
        return this.promotions.contains(promotion);
    }

}