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
    protected static String benefitName = "크리스마스 디데이 할인";

    protected Optional<Integer> amountOfBenefit;

    void addResultIfExist(List<String> willBeAdded) {
        amountOfBenefit.ifPresent(amount ->
                willBeAdded.add(getBenefitStringOf(amount)));
    }

    protected String getBenefitStringOf(Integer amount) {
        return benefitName + SEPARATOR + MINUS + amount + WON;
    }

}
