package christmas.promotion.collborator.calendar.benefit;

import java.util.Optional;

public final class WeekdayBenefitWrapper extends BenefitWrapper {

    public WeekdayBenefitWrapper(Optional<Integer> amountOfWeekday) {
        this.amountOfBenefit = amountOfWeekday;
        benefitName = "평일 할인";
    }

}
