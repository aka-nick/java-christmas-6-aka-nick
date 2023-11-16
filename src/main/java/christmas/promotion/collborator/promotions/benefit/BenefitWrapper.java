package christmas.promotion.collborator.promotions.benefit;

import static christmas.promotion.enums.collaborator.promotions.benefit.BenefitWrapperMessage.MINUS;
import static christmas.promotion.enums.collaborator.promotions.benefit.BenefitWrapperMessage.SEPARATOR;

import christmas.promotion.collborator.generic.Won;
import java.util.List;
import java.util.Optional;

public sealed abstract class BenefitWrapper permits
        DDayBenefitWrapper,
        SpecialBenefitWrapper,
        WeekdayBenefitWrapper,
        WeekendBenefitWrapper,
        GiveawayBenefitWrapper {

    protected String benefitName = "";

    protected Optional<Won> amountOfBenefit;

    void addMessageIfBenefitExists(List<String> willBeAdded) {
        amountOfBenefit
                .filter(amount -> 0 < amount.intValue())
                .ifPresent(amount -> willBeAdded.add(getBenefitStringOf(amount)));
    }

    protected String getBenefitStringOf(Won formattedAmount) {
        return benefitName + SEPARATOR.get() + MINUS.get() + formattedAmount;
    }

    public boolean isEmpty() {
        return amountOfBenefit.isEmpty();
    }

    public Won orElse(Won other) {
        return amountOfBenefit.orElse(other);
    }

    public boolean equalsBenefitName(String other) {
        return benefitName.equals(other);
    }

}
