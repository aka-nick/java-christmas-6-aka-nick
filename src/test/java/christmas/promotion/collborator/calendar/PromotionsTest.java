package christmas.promotion.collborator.calendar;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.promotion.collborator.calendar.benefit.BenefitAmount;
import christmas.promotion.collborator.calendar.benefit.BenefitWrapper;
import christmas.promotion.collborator.generic.Won;
import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.Orders;
import java.util.List;
import org.junit.jupiter.api.Test;

class PromotionsTest {

    private static final Date fakeDate = Calendar.findDate(1).get();
    private static final Date weekendDate = Calendar.findDate(29).get();
    private static final Date weekdayDate = Calendar.findDate(27).get();
    private static final Date specialDate = Calendar.findDate(31).get();
    private static final Date christmasDate = Calendar.findDate(25).get();
    public static final Orders 육천원짜리주문 = new Orders(List.of(Order.place("양송이수프", 1)));
    public static final Orders 오만원짜리주문 = new Orders(List.of(Order.place("크리스마스파스타", 2)));
    public static final Orders 이십만원짜리주문 = new Orders(List.of(Order.place("크리스마스파스타", 6), Order.place("아이스크림", 8)));

    @Test
    void 총결제금액이_이벤트조건_미만의금액인경우_혜택없음() {
        Promotions promotions = new Promotions(fakeDate, 육천원짜리주문);
        BenefitAmount benefitAmount = promotions.askBenefitAmount();

        Won expected = Won.of(0);
        Won actual = benefitAmount.amountOfTotalBenefits();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 총결제금액이_할인이벤트조건초과면서_증정이벤트조건미만일경우_증정품없음() {

        Promotions promotions = new Promotions(fakeDate, 오만원짜리주문);
        BenefitAmount benefitAmount = promotions.askBenefitAmount();

        Won actual1 = benefitAmount.amountOfTotalBenefits();
        Boolean actual2 = benefitAmount.amountOfBenefits().stream()
                .filter(benefitWrapper -> benefitWrapper.equalsBenefitName("증정 이벤트"))
                .map(BenefitWrapper::isEmpty)
                .findFirst().get();
        Won unexpected1 = Won.of(0);

        assertThat(actual1).isNotEqualTo(unexpected1);
        assertThat(actual2).isTrue();
    }

    @Test
    void 크리스마스에_모든이벤트혜택을_다받는경우() {
        Promotions promotions = new Promotions(christmasDate, 이십만원짜리주문);
        BenefitAmount benefitAmount = promotions.askBenefitAmount();

        String benefitMessages = benefitAmount.findBenefitMessages();

        assertThat(benefitMessages).contains("크리스마스 디데이 할인", "특별 할인", "증정 이벤트");
    }

    @Test
    void 크리스마스에_받을수있는모든이벤트혜택을_다받는경우() {
        Promotions promotions = new Promotions(christmasDate, 이십만원짜리주문);
        BenefitAmount benefitAmount = promotions.askBenefitAmount();

        String benefitMessages = benefitAmount.findBenefitMessages();

        assertThat(benefitMessages).contains("크리스마스 디데이 할인", "특별 할인", "평일 할인", "증정 이벤트");
    }

    @Test
    void 크리스마스가지나간일요일에_받을수있는모든이벤트혜택을_다받는경우() {
        Promotions promotions = new Promotions(specialDate, 이십만원짜리주문);
        BenefitAmount benefitAmount = promotions.askBenefitAmount();

        String benefitMessages = benefitAmount.findBenefitMessages();

        assertThat(benefitMessages).contains("특별 할인", "평일 할인", "증정 이벤트");
    }

    @Test
    void 크리스마스가지나간주말에_받을수있는모든이벤트혜택을_다받는경우() {
        Promotions promotions = new Promotions(weekendDate, 이십만원짜리주문);
        BenefitAmount benefitAmount = promotions.askBenefitAmount();

        String benefitMessages = benefitAmount.findBenefitMessages();

        assertThat(benefitMessages).contains("주말 할인", "증정 이벤트");
    }

    @Test
    void 크리스마스가지나간평일에_받을수있는모든이벤트혜택을_다받는경우() {
        Promotions promotions = new Promotions(weekdayDate, 이십만원짜리주문);
        BenefitAmount benefitAmount = promotions.askBenefitAmount();

        String benefitMessages = benefitAmount.findBenefitMessages();

        assertThat(benefitMessages).contains("평일 할인", "증정 이벤트");
    }

}