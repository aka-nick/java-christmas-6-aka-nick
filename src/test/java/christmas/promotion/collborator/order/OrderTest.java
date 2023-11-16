package christmas.promotion.collborator.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void place메서드를통해_정상적으로생성할수있다() {
        String expectedName = "타파스";
        int expectedQuantity = 20;
        Order order = Order.place(expectedName, expectedQuantity);

        assertThat(order).isNotNull();
        assertThat(order.foodName()).isEqualTo(expectedName);
        assertThat(order.foodQuantity()).isEqualTo(expectedQuantity);
    }

    @Test
    void place메서드를통해_비정상생성시_예외가발생한다() {
        String expectedName = "타파스";
        int invalidQuantity = 25;

        assertThatThrownBy(() -> Order.place(expectedName, invalidQuantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음료를시키면_isBeverage_결과값이true() {
        Order beverageOrder = Order.place("제로콜라", 3);

        assertThat(beverageOrder.isBeverage()).isTrue();
    }

    @Test
    void 음료가아닌걸시키면_isBeverage_결과값이false() {
        Order nonBeverageOrder = Order.place("타파스", 2);

        assertThat(nonBeverageOrder.isBeverage()).isFalse();
    }

    @Test
    void 메인을시키면_isMain_결과값이true() {
        Order mainDishOrder = Order.place("크리스마스파스타", 2);

        assertThat(mainDishOrder.isMain()).isTrue();
    }

    @Test
    void 메인을안시키면_isMain_결과값이false() {
        Order nonMainDishOrder = Order.place("제로콜라", 3);

        assertThat(nonMainDishOrder.isMain()).isFalse();
    }

    @Test
    void 디저트를시키면_isDessert_결과값이true() {
        Order dessertOrder = Order.place("초코케이크", 2);

        assertThat(dessertOrder.isDessert()).isTrue();
    }

    @Test
    void 디저트를시키면_isDessert_결과값이false() {
        Order nonDessertOrder = Order.place("레드와인", 2);

        assertThat(nonDessertOrder.isDessert()).isFalse();
    }

    @Test
    void foodPrice로_주문가격확인가능하다() {
        int expectedPrice = 180000;
        Order order = Order.place("레드와인", 3);

        // when, then
        assertThat(order.foodPrice()).isEqualTo(expectedPrice); // 가격은 테스트를 위해 간단하게 계산한 예시입니다.
    }

}