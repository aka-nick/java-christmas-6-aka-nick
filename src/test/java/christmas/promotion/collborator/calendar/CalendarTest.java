package christmas.promotion.collborator.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.promotion.collborator.promotions.Promotions;
import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.Orders;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class CalendarTest {

    public static final int NUMBER_OF_VALID_DATE = 24;

    @Test
    void 정상적으로날짜객체를조회해올수있다() {
        Optional<Date> validDate = Calendar.findDate(NUMBER_OF_VALID_DATE);

        assertThat(validDate.isPresent()).isTrue();
        assertThat(validDate.get().date()).isEqualTo(NUMBER_OF_VALID_DATE);
    }

    @Test
    void 최소날짜보다작은비정상적인날짜조회시_빈옵셔널반환() {
        assertThat(Calendar.findDate(0).isEmpty()).isTrue();
    }

    @Test
    void 최대날짜보다큰비정상적인날짜조회시_빈옵셔널반환() {
        assertThat(Calendar.findDate(300).isEmpty()).isTrue();
    }

    @Test
    void 정상적인날짜로해당일자의프로모션조회가능() {
        Orders orders = new Orders(List.of(Order.place("크리스마스파스타", 4)));

        Promotions expected = new Promotions(Calendar.findDate(NUMBER_OF_VALID_DATE).get(), orders);
        Promotions actual = Calendar.findPromotionsBy(Calendar.findDate(NUMBER_OF_VALID_DATE).get(), orders);

        assertThat(actual.askBenefitAmount())
                .isEqualTo(expected.askBenefitAmount());
    }

}