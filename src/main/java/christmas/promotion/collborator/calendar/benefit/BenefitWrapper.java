package christmas.promotion.collborator.calendar.benefit;

import java.util.List;
import java.util.Optional;

public sealed abstract class BenefitWrapper permits
        DDayBenefitWrapper,
        SpecialBenefitWrapper,
        WeekdayBenefitWrapper,
        WeekendBenefitWrapper,
        GiveawayBenefitWrapper {

    private static final String SEPARATOR = ": ";
    private static final String MINUS = "-";
    private static final String WON = "원";
    protected String benefitName = "";

    protected Optional<Integer> amountOfBenefit;

    void addMessageIfBenefitExists(List<String> willBeAdded) {
        amountOfBenefit.ifPresent(amount ->
                willBeAdded.add(getBenefitStringOf(amount)));
    }

    protected String getBenefitStringOf(Integer amount) {
        return benefitName + SEPARATOR + MINUS + amount + WON;
    }

    public boolean isEmpty() {
        return amountOfBenefit.isEmpty();
    }

    public Integer orElse(Integer other) {
        return amountOfBenefit.orElse(other);
    }

    public boolean equalsBenefitName(String other) {
        return benefitName.equals(other);
    }

}
