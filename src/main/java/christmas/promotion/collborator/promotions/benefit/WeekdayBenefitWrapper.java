package christmas.promotion.collborator.promotions.benefit;

import static christmas.promotion.enums.collaborator.promotions.benefit.WeekdayBenefitWrapperMessage.WEEKDAY_BENEFIT_MESSAGE;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class WeekdayBenefitWrapper extends BenefitWrapper {

    public WeekdayBenefitWrapper(Optional<Won> amountOfWeekday) {
        this.amountOfBenefit = amountOfWeekday;
        benefitName = WEEKDAY_BENEFIT_MESSAGE.get();
    }

}
