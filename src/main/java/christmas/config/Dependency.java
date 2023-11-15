package christmas.config;

import christmas.promotion.io.ConsoleInput;
import christmas.promotion.io.ConsoleOutput;
import christmas.promotion.organizer.PlannerOrganizer;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;
import christmas.promotion.organizer.manager.OrderManager;
import christmas.promotion.organizer.manager.PromotionManager;
import christmas.promotion.organizer.manager.ReservationManager;

public class Dependency {

    private static final Input INPUT = new ConsoleInput();
    private static final Output OUTPUT = new ConsoleOutput();

    public static PlannerOrganizer promotionOrganizer() {
        return new PlannerOrganizer(reservationManager(), orderManager(), promotionPlanner());
    }

    public static Input input() {
        return INPUT;
    }

    public static Output output() {
        return OUTPUT;
    }

    public static ReservationManager reservationManager() {
        return new ReservationManager(input(), output());
    }

    public static OrderManager orderManager() {
        return new OrderManager(input(), output());
    }

    public static PromotionManager promotionPlanner() {
        return new PromotionManager(output());
    }

}
