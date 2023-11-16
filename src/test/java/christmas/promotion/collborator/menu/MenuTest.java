package christmas.promotion.collborator.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void 정상적인메뉴명을_findby로_조회할수있다() {
        String expectedFoodName = "양송이수프";

        Optional<Food> actualFood = Menu.findBy(expectedFoodName);

        assertThat(actualFood).isPresent();
        assertThat(actualFood.get().getName()).isEqualTo(expectedFoodName);
    }

    @Test
    void 없는메뉴찾으면_비어있다() {
        assertThat(Menu.findBy("김치전")).isEmpty();
    }

    @Test
    void null로찾으려고하면_비어있다() {
        // when, then
        assertThat(Menu.findBy(null)).isEmpty();
    }

}