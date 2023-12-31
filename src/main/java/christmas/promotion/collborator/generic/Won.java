package christmas.promotion.collborator.generic;

import static christmas.promotion.enums.collaborator.generic.WonMessage.UNIT_OF_WON;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;

public record Won(BigInteger amount) {

    public static Won of (BigInteger amount) {
        return new Won(amount);
    }
    public static Won of(long amount) {
        return new Won(BigInteger.valueOf(amount));
    }
    public static Won of(int amount) {
        return new Won(BigInteger.valueOf(amount));
    }

    public int intValue() {
        return amount.intValue();
    }

    public Won minus(Won other) {
        return Won.of(this.amount.subtract(other.amount));
    }

    public String format() {
        return NumberFormat.getNumberInstance(Locale.KOREAN).format(amount) + UNIT_OF_WON.get();
    }

    @Override
    public String toString() {
        return format();

    }

}
