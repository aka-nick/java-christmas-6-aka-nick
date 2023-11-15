package christmas.promotion.collborator.generic;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class WonTest {

    @Test
    void 화폐만들기_기본생성_성공() {
        int expected = 1000;

        Won won = Won.of(BigInteger.valueOf(expected));

        assertThat(won).isNotNull();
        assertThat(won.intValue()).isEqualTo(expected);
    }

    @Test
    void 정수값으로_화폐만들기_성공() {
        int expected = 200;
        Won won = Won.of(expected);

        assertThat(won).isNotNull();
        assertThat(won.intValue()).isEqualTo(expected);
    }

    @Test
    void 정수값조회가_가능하다() {
        int expected = 100;
        Won won = Won.of(expected);

        assertThat(won.intValue()).isEqualTo(expected);
    }

    @Test
    void 감산이_가능하다() {
        Won won1 = Won.of(1000);
        Won won2 = Won.of(500);

        Won actual = won1.minus(won2);
        int expected = 500;

        assertThat(actual.intValue()).isEqualTo(expected);
    }

    @Test
    void format으로_정돈된문자열획득가능() {
        String actualFormatted = Won.of(1500).format();
        String expected = "1,500원";

        assertThat(actualFormatted).isEqualTo(expected);
    }

    @Test
    void 같은값에대하여_동등비교가_성공() {
        Won left = Won.of(1000);
        Won right = Won.of(1000L);

        assertThat(left).isEqualTo(right);
    }

    @Test
    void 다른값에대하여_동등비교가_실패() {
        Won left = Won.of(BigInteger.ZERO);
        Won right = Won.of(BigInteger.valueOf(300));

        assertThat(left).isNotEqualTo(right);
    }

}