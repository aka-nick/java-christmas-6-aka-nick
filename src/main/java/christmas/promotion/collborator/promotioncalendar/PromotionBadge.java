package christmas.promotion.collborator.promotioncalendar;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum PromotionBadge {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NOTHING("없음", 0),
    ;

    private final String badgeName;
    private final int criteriaAmountOfBadge;

    PromotionBadge(String badgeName, int criteriaAmountOfBadge) {
        this.badgeName = badgeName;
        this.criteriaAmountOfBadge = criteriaAmountOfBadge;
    }

    public static String findPromotionBadgeBy(int amountOfTotalBenefits) {
        return PromotionBadge.valuesInOrderAmount().stream()
                .filter(badge -> badge.criteriaAmountOfBadge <= amountOfTotalBenefits)
                .map(badge -> badge.badgeName)
                .findFirst()
                .orElse("없음");
    }

    private static List<PromotionBadge> valuesInOrderAmount() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingInt(value -> -value.criteriaAmountOfBadge))
                .toList();
    }


}
