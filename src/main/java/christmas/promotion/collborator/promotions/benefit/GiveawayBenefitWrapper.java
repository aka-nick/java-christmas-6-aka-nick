package christmas.promotion.collborator.promotions.benefit;

import static christmas.promotion.enums.collaborator.promotions.benefit.GiveawayBenefitWrapperMessage.GIVEAWAY_BENEFIT_MESSAGE;

import christmas.promotion.collborator.generic.Won;
import java.util.Optional;

public final class GiveawayBenefitWrapper extends BenefitWrapper {

    public GiveawayBenefitWrapper(Optional<Won> amountOfGiveaway) {
        this.amountOfBenefit = amountOfGiveaway;
        benefitName = GIVEAWAY_BENEFIT_MESSAGE.get();
    }

}
