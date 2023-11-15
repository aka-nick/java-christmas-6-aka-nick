package christmas.promotion.collborator.promotions.benefit;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class WeekdayBenefitWrapper extends BenefitWrapper {

    public WeekdayBenefitWrapper(Optional<Won> amountOfWeekday) {
        this.amountOfBenefit = amountOfWeekday;
        benefitName = "평일 할인";
    }

}
