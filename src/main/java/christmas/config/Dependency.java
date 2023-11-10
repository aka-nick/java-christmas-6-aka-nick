package christmas.config;

import christmas.promotion.organizer.PromotionPlanner;

public class Dependency {

    public static PromotionPlanner promotionPlanner() {
        return new PromotionPlanner();
    }

}
