package christmas.promotion.collborator.promotions.benefit;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class WeekendBenefitWrapper extends BenefitWrapper {

    public WeekendBenefitWrapper(Optional<Won> amountOfWeekend) {
        this.amountOfBenefit = amountOfWeekend;
        benefitName = "주말 할인";
    }

}
