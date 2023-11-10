package christmas.config;

import christmas.promotion.organizer.PlannerOrganizer;

public class Dependency {

    public static PlannerOrganizer promotionPlanner() {
        return new PlannerOrganizer();
    }

}
