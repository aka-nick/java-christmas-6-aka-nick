package christmas.promotion.collborator.generic;

import java.math.BigInteger;

public class Money {

    private final BigInteger amount;

    private Money(BigInteger amount) {
        this.amount = amount;
    }

    public static Money won(long amount) {
        return new Money(BigInteger.valueOf(amount));
    }

}
