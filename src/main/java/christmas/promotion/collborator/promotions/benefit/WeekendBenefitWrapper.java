package christmas.promotion.collborator.promotions.benefit;

import static christmas.promotion.enums.collaborator.promotions.benefit.WeekendBenefitWrapperMessage.WEEKEND_BENEFIT_MESSAGE;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class WeekendBenefitWrapper extends BenefitWrapper {

    public WeekendBenefitWrapper(Optional<Won> amountOfWeekend) {
        this.amountOfBenefit = amountOfWeekend;
        benefitName = WEEKEND_BENEFIT_MESSAGE.get();
    }

}
