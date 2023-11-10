package christmas.promotion.external.io;

import christmas.promotion.organizer.io.Output;

public class ConsoleOutput implements Output {

    @Override
    public void print(final Object message) {
        System.out.print(message.toString());
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(final Object message) {
        System.out.println(message.toString());
    }

}
