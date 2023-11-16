package christmas.promotion.collborator.promotions.benefit;

import static christmas.promotion.enums.collaborator.promotions.benefit.DDayBenefitWrapperMessage.D_DAY_BENEFIT_MESSAGE;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class DDayBenefitWrapper extends BenefitWrapper {

    public DDayBenefitWrapper(Optional<Won> amountOfDDay) {
        this.amountOfBenefit = amountOfDDay;
        benefitName = D_DAY_BENEFIT_MESSAGE.get();
    }

}
