package christmas.promotion.io;

import static christmas.promotion.enums.io.ConsoleInputMessage.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.promotion.organizer.io.Input;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class ConsoleInput implements Input {

    private static final String DEFAULT_SPLIT_REGEX = "";

    public String string() {
        return Console.readLine();
    }

    public List<String> strings() {
        return strings(DEFAULT_SPLIT_REGEX);
    }

    public List<String> strings(final String regex) {
        return Arrays.stream(Console.readLine().split(regex))
                .toList();
    }

    public int number() {
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(EXCEPTION_WRONG_INPUT_VALUE.get(), e);
        }
    }

    public List<Integer> numbers() {
        return numbers(DEFAULT_SPLIT_REGEX);
    }

    public List<Integer> numbers(final String regex) {
        try {
            return Arrays.stream(Console.readLine().split(regex))
                    .map(Integer::valueOf)
                    .toList();
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(EXCEPTION_WRONG_SPLIT_REGEX.get(), e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(EXCEPTION_WRONG_INPUT_VALUE.get());
        }
    }

}