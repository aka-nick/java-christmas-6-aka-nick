package christmas.promotion.collborator.promotions.benefit;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class SpecialBenefitWrapper extends BenefitWrapper {

    public SpecialBenefitWrapper(Optional<Won> amountOfSpecial) {
        this.amountOfBenefit = amountOfSpecial;
        benefitName = "특별 할인";
    }

}
