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

    /**
     * 반환값이 없는 반복 인터랙션. 예외 상황 시, 주입한 예외를 발생시킨다.
     * 새로운 예외를 발생시키는 것이기 때문에 root cause를 잃어버린다.
     * 즉, 이 메서드를 사용할 때는 발생 위치가 아주 특정되고, 중복 재사용되지 않으며,
     * 좁은 의미를 가진 CustomException을 일으키는 경우에만 한하여 사용하여야 한다.
     */
    default void runInteractionWithException(final Runnable runnable, RuntimeException exception) {
        while (true) {
            try {
                runInteractionWithExceptionInner(runnable, exception);
                break;
            } catch (RuntimeException e) {
                output.println(GlobalMessage.EXCEPTION_HEADER.get() + e.getMessage());
            }
        }
    }

    private void runInteractionWithExceptionInner(final Runnable runnable, final RuntimeException exception) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            throw exception;
        }
    }

    /**
     * 반환값이 있는 반복 인터랙션. 예외 상황 시, 주입한 예외를 발생시킨다.
     * 새로운 예외를 발생시키는 것이기 때문에 root cause를 잃어버린다.
     * 즉, 이 메서드를 사용할 때는 발생 위치가 아주 특정되고, 중복 재사용되지 않으며,
     * 좁은 의미를 가진 CustomException을 일으키는 경우에만 한하여 사용하여야 한다.
     */
    default <T> T supplyInteractionWithException(final Supplier<T> supplier, IllegalArgumentException exception) {
        while (true) {
            try {
                return supplyInteractionWithExceptionInner(supplier, exception);
            } catch (IllegalArgumentException e) {
                output.println(GlobalMessage.EXCEPTION_HEADER.get() + e.getMessage());
            }
        }
    }

    private <T> T supplyInteractionWithExceptionInner(final Supplier<T> supplier, IllegalArgumentException exception) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            throw exception;
        }
    }

}