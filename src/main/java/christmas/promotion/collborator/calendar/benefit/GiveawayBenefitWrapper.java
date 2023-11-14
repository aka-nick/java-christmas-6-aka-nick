package christmas.promotion.collborator.calendar.benefit;

import java.util.Optional;

public final class GiveawayBenefitWrapper extends BenefitWrapper {

    public GiveawayBenefitWrapper(Optional<Integer> amountOfGiveaway) {
        this.amountOfBenefit = amountOfGiveaway;
        benefitName = "증정 이벤트";
    }

}
