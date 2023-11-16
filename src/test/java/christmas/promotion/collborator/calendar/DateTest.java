package christmas.promotion.collborator.calendar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void 동일한값을가진날짜객체는_동등비교true() {
        Date left = Date.of(1);
        Date right = Date.of(1);
        assertThat(left).isEqualTo(right);
    }

}