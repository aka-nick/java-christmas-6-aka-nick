package christmas.promotion.collborator.calendar.benefit;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class DDayBenefitWrapper extends BenefitWrapper {

    public DDayBenefitWrapper(Optional<Won> amountOfDDay) {
        this.amountOfBenefit = amountOfDDay;
        benefitName = "크리스마스 디데이 할인";
    }

}
