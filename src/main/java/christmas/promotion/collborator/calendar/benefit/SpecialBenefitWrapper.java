package christmas.promotion.collborator.calendar.benefit;

import java.util.Optional;

public final class SpecialBenefitWrapper extends BenefitWrapper {

    public SpecialBenefitWrapper(Optional<Integer> amountOfSpecial) {
        this.amountOfBenefit = amountOfSpecial;
        benefitName = "특별 할인";
    }

}
