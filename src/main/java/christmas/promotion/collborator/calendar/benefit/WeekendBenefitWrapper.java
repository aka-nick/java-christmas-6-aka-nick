package christmas.promotion.collborator.calendar.benefit;

import java.util.Optional;

public final class WeekendBenefitWrapper extends BenefitWrapper {

    public WeekendBenefitWrapper(Optional<Integer> amountOfWeekend) {
        this.amountOfBenefit = amountOfWeekend;
        benefitName = "주말 할인";
    }

}
