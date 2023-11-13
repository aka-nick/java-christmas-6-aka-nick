package christmas.config;

import christmas.promotion.io.ConsoleInput;
import christmas.promotion.io.ConsoleOutput;
import christmas.promotion.organizer.PlannerOrganizer;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;
import christmas.promotion.organizer.viewer.OrderManager;
import christmas.promotion.organizer.viewer.ReservationManager;

public class Dependency {

    private static final Input INPUT = new ConsoleInput();
    private static final Output OUTPUT = new ConsoleOutput();

    public static PlannerOrganizer promotionPlanner() {
        return new PlannerOrganizer(input(), output(), reservationManager(), orderManager());
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

}
