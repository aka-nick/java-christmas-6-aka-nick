package christmas.promotion.collborator.calendar;

import christmas.promotion.collborator.menu.Menu;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.exception.InvalidReservationOrderException;
import java.util.Optional;

public class Promotions {

    private static final int CRITERIA_AMOUNT_FOR_EVENT = 10_000;
    private static final int CRITERIA_AMOUNT_FOR_GIVEAWAY = 120_000;
    private static final int DEFAULT_AMOUNT_OF_D_DAY_DISCOUNT = 1_000;
    private static final int INCREASE_AMOUNT_OF_D_DAY_DISCOUNT = 100;
    private static final int AMOUNT_OF_WEEK_DISCOUNT = 2_023;
    private static final int AMOUNT_OF_SPECIAL_DISCOUNT = 1_000;

    private final Date today;
    private final Orders orders;
    private final int totalPaymentAmount;

    protected Promotions(Date todayDate, Orders orders) {
        this.today = todayDate;
        this.orders = orders;
        this.totalPaymentAmount = orders.calculateTotalPrice();
    }

    public BenefitAmount askBenefitAmount() {
        if (totalPaymentAmount < CRITERIA_AMOUNT_FOR_EVENT) {
            return new BenefitAmount(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());
        }

        return new BenefitAmount(
                calculateGiveAwayAmount(),
                calculateDDayAmount(),
                calculateWeekendAmount(),
                calculateWeekdayAmount(),
                calculateSpecialAmount());
    }

    private Optional<Integer> calculateGiveAwayAmount() {
        if (totalPaymentAmount < CRITERIA_AMOUNT_FOR_GIVEAWAY) {
            return Optional.empty();
        }
        return Optional.of(
                Menu.findBy("샴페인")
                        .orElseThrow(InvalidReservationOrderException::new)
                        .getPrice());
    }

    private Optional<Integer> calculateDDayAmount() {
        if (!today.contains(Promotion.D_DAY)) {
            return Optional.empty();
        }
        final int dayOfIncrease = today.date() - 1;
        return Optional.of(
                DEFAULT_AMOUNT_OF_D_DAY_DISCOUNT + (dayOfIncrease * INCREASE_AMOUNT_OF_D_DAY_DISCOUNT));
    }

    private Optional<Integer> calculateWeekendAmount() {
        if (!today.contains(Promotion.WEEKEND)) {
            return Optional.empty();
        }
        return Optional.of(
                orders.countMainOrders() * AMOUNT_OF_WEEK_DISCOUNT);
    }

    private Optional<Integer> calculateWeekdayAmount() {
        if (!today.contains(Promotion.WEEKDAY)) {
            return Optional.empty();
        }
        return Optional.of(
                orders.countDessertOrders() * AMOUNT_OF_WEEK_DISCOUNT);
    }

    private Optional<Integer> calculateSpecialAmount() {
        if (!today.contains(Promotion.SPECIAL)) {
            return Optional.empty();
        }
        return Optional.of(AMOUNT_OF_SPECIAL_DISCOUNT);
    }
    /*
  - 반드시 연산값1이 연산값2보다 우선해야 함.
  - 연산값1의 내부 계산 결과들을 연산값2와 ‘논리적으로' 공유해야 함. -> 이 부분은 비공개 메서드로 추출하자
  - 일단 이렇게 해놓고, 그래도 너무 지저분하면 클래스 단위로 리팩토링 하자.
  */

    // 연산값1(혜택금액)을 반환하는 메서드
    // 연산값2(토탈결과메시지)을 반환하는 메서드
    // 연산값3(적용금액)을 반환하는 메서드

/* 여기부터 아래에 닫히기 까지의 행위는 사실 밖에 공개될 필요가 없다.
  public boolean whetherApplyEvents() {
    return CRITERIA_AMOUNT_FOR_EVENT_APPLY <= amountOfEventApplyPrice;
  }

  public boolean whetherApplyDDay() {
    return promotions.contains(Promotion.D_DAY);
  }
  public boolean whetherApplyWeekend() {
    return promotions.contains(Promotion.WEEKEND);
  }
  public boolean whetherApplyWeekday() {
    return promotions.contains(Promotion.WEEKDAY);
  }
  public boolean whetherApplySpecial() {
    return promotions.contains(Promotion.SPECIAL);
  }
*/


}
