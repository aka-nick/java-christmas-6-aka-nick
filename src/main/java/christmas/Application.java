package christmas;

import christmas.config.Dependency;
import christmas.promotion.organizer.PromotionPlanner;

public class Application {
    public static void main(String[] args) {
        try {
            PromotionPlanner planner = Dependency.promotionPlanner();
            planner.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
