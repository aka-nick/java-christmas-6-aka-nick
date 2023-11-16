package christmas;

import christmas.config.Dependency;
import christmas.promotion.organizer.PlannerOrganizer;

public class Application {

    public static void main(String[] args) {
        try {
            PlannerOrganizer organizer = Dependency.promotionOrganizer();
            organizer.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
