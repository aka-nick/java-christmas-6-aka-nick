package christmas.promotion.collborator.generic;

import java.math.BigInteger;

public class Won {

    private final BigInteger amount;

    private Won(BigInteger amount) {
        this.amount = amount;
    }

    public static Won won(long amount) {
        return new Won(BigInteger.valueOf(amount));
    }

}
