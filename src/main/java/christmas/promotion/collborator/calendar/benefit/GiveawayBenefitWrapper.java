package christmas.promotion.collborator.calendar.benefit;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class GiveawayBenefitWrapper extends BenefitWrapper {

    public GiveawayBenefitWrapper(Optional<Won> amountOfGiveaway) {
        this.amountOfBenefit = amountOfGiveaway;
        benefitName = "증정 이벤트";
    }

}
