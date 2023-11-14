package christmas.promotion.collborator.calendar.benefit;

import java.util.Optional;

public record BenefitAmount(Optional<Integer> amountOfGiveaway,
                            Optional<Integer> amountOfDDay,
                            Optional<Integer> amountOfWeekend,
                            Optional<Integer> amountOfWeekday,
                            Optional<Integer> amountOfSpecial) {

    // TODO : 리터럴 제거
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

    public Integer amountOfTotalBenefits() {
        return amountOfGiveaway.orElse(0)
                + amountOfDDay.orElse(0)
                + amountOfWeekend.orElse(0)
                + amountOfWeekday.orElse(0)
                + amountOfSpecial.orElse(0);
    }

}