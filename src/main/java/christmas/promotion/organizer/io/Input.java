package christmas.promotion.organizer.io;

import java.util.List;

public interface Input {

    String string();

    List<String> strings();

    List<String> strings(final String regex);

    int number();

    List<Integer> numbers();

    List<Integer> numbers(final String regex);

}
