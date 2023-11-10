package christmas.config;

import christmas.promotion.external.io.ConsoleInput;
import christmas.promotion.external.io.ConsoleOutput;
import christmas.promotion.organizer.PlannerOrganizer;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;

public class Dependency {

    private static final Input INPUT = new ConsoleInput();
    private static final Output OUTPUT = new ConsoleOutput();

    public static PlannerOrganizer promotionPlanner() {
        return new PlannerOrganizer(input(), output());
    }

    private static Input input() {
        return INPUT;
    }

    private static Output output() {
        return OUTPUT;
    }

}
