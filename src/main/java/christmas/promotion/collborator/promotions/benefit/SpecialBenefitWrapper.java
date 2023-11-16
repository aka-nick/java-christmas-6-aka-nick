package christmas.promotion.collborator.promotions.benefit;

import static christmas.promotion.enums.collaborator.promotions.benefit.SpecialBenefitWrapperMessage.SPECIAL_BENEFIT_MESSAGE;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class SpecialBenefitWrapper extends BenefitWrapper {

    public SpecialBenefitWrapper(Optional<Won> amountOfSpecial) {
        this.amountOfBenefit = amountOfSpecial;
        benefitName = SPECIAL_BENEFIT_MESSAGE.get();
    }

}
