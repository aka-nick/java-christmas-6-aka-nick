package christmas.promotion.collborator.promotioncalendar;

import java.util.Optional;

public record BenefitAmount(Optional<Integer> amountOfGiveaway,
                            Optional<Integer> amountOfDDay,
                            Optional<Integer> amountOfWeekend,
                            Optional<Integer> amountOfWeekday,
                            Optional<Integer> amountOfSpecial) {

    public String askResultOfGiveaway() {
        if (amountOfGiveaway().isEmpty()) {
            return "없음";
        }
        return "샴페인 1개";
    }

    public boolean isAllDiscountEmpty() {
        return amountOfGiveaway().isEmpty()
                && amountOfDDay().isEmpty()
                && amountOfWeekend().isEmpty()
                && amountOfWeekday().isEmpty()
                && amountOfSpecial().isEmpty();
    }

}