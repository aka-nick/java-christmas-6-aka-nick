package christmas.promotion.collborator.calendar.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class BenefitAmountTest {

    @Test
    void 같은값을가진객체끼리_동등비교시_true() {
        BenefitAmount first = new BenefitAmount(List.of(new GiveawayBenefitWrapper(Optional.of(100))));
        BenefitAmount second = new BenefitAmount(List.of(new GiveawayBenefitWrapper(Optional.of(100))));

        assertThat(first).isEqualTo(second);
    }

}