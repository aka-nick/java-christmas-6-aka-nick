package christmas.promotion.collborator.calendar.benefit;

import christmas.promotion.messages.GlobalMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record BenefitAmount(List<BenefitWrapper> amountOfBenefits) {

    public static final String NO_BENEFITS = "없음";
    public static final String ITEM_OF_GIVEAWAY = "샴페인 1개";

    public String askResultOfGiveaway() {
        if (isNotFoundOfGiveaway()) {
            return NO_BENEFITS;
        }
        return ITEM_OF_GIVEAWAY;
    }

    private boolean isNotFoundOfGiveaway() {
        return amountOfBenefits.stream()
                .noneMatch(benefit -> benefit.equalsBenefitName("증정 이벤트"));
    }

    public boolean isAllDiscountEmpty() {
        return amountOfBenefits.stream()
                .allMatch(BenefitWrapper::isEmpty);
    }

    public Integer amountOfTotalBenefits() {
        return amountOfBenefits.stream()
                .mapToInt(benefit -> benefit.orElse(0))
                .sum();
    }

    public String findBenefitMessages() {
        List<String> benefitMessages = new ArrayList<>();
        amountOfBenefits.forEach(benefit ->
                benefit.addMessageIfBenefitExists(benefitMessages));

        if (benefitMessages.isEmpty()) {
            return NO_BENEFITS;
        }
        return benefitMessages.stream()
                .collect(Collectors.joining(GlobalMessage.NEW_LINE.get()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BenefitAmount that = (BenefitAmount) o;

        List<String> thisBenefitNames = amountOfBenefits.stream()
                .map(thisBenefitWrapper -> thisBenefitWrapper.benefitName)
                .toList();
        List<String> thatBenefitNames = that.amountOfBenefits.stream()
                .map(thisBenefitWrapper -> thisBenefitWrapper.benefitName)
                .toList();

        return Objects.equals(thisBenefitNames, thatBenefitNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfBenefits);
    }

}