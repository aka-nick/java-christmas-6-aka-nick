package christmas.promotion.organizer.io;

import christmas.config.Dependency;
import christmas.promotion.enums.GlobalMessage;
import java.util.function.Supplier;

public interface InteractionRepeatable {

    Output output = Dependency.output();

    default void runInteraction(final Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (IllegalArgumentException e) {
                output.println(GlobalMessage.EXCEPTION_HEADER.get() + e.getMessage());
            }
        }
    }

    default <T> T supplyInteraction(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                output.println(GlobalMessage.EXCEPTION_HEADER.get() + e.getMessage());
            }
        }
    }

}