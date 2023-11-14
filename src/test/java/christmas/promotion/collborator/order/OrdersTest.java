package christmas.promotion.collborator.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class OrdersTest {

    @Test
    void 음료만시키면_생성시예외가발생한다() {
        List<Order> beverageOrders = List.of(
                Order.place("제로콜라", 3),
                Order.place("레드와인", 5)
        );

        assertThatThrownBy(() -> new Orders(beverageOrders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은음식중복으로시키면_생성시예외가발생한다() {
        List<Order> duplicateOrders = List.of(
                Order.place("타파스", 5),
                Order.place("레드와인", 5),
                Order.place("레드와인", 1)
        );

        assertThatThrownBy(() -> new Orders(duplicateOrders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 너무많이시키면_생성시예외가발생한다() {
        List<Order> duplicateOrders = List.of(
                Order.place("타파스", 500)
        );

        assertThatThrownBy(() -> new Orders(duplicateOrders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // Orders 클래스의 calculateTotalPrice 메서드를 테스트하는 코드
    @Test
    void 주문한메뉴의_가격총합을_반환할수있다() {
        List<Order> orders = List.of(
                Order.place("양송이수프", 1),
                Order.place("제로콜라", 1)
        );
        Orders orderList = new Orders(orders);
        int actual = orderList.calculateTotalPrice();
        int expected = 6000 + 3000;

        assertThat(actual).isEqualTo(expected);
    }

}