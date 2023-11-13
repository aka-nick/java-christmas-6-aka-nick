package christmas.promotion.collborator.promotioncalendar;

import christmas.promotion.collborator.order.Orders;
import java.util.Optional;

public class Promotions {

    private static final int CRITERIA_AMOUNT_FOR_EVENT = 10_000;

    private final Date today;
    private final Orders orders;
    private final int totalPaymentAmount;

    protected Promotions(Date todayDate, Orders orders) {
        this.today = todayDate;
        this.orders = orders;
        this.totalPaymentAmount = orders.calculateTotalPrice();
    }

    public BenefitAmount askBenefitAmountOf(Promotion promotion) {
        if (totalPaymentAmount < CRITERIA_AMOUNT_FOR_EVENT) {
            return new BenefitAmount(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty());
        }

        return new BenefitAmount(
                calculateDDayAmount(),
                calculateWeekendAmount(),
                calculateWeekdayAmount(),
                calculateSpecialAmount());
    }

    private Optional<Integer> calculateDDayAmount() {
        if (!today.contains(Promotion.D_DAY)) {
            return Optional.empty();
        }

        final int dDayBasicAmount = 1000;
        final int increaseUnit = 100;
        final int dayOfIncrease = today.date() - 1;
        return Optional.of(
                dDayBasicAmount + (dayOfIncrease * increaseUnit));
    }

    private Optional<Integer> calculateWeekendAmount() {
        if (!today.contains(Promotion.WEEKEND)) {
            return Optional.empty();
        }

        long countOfMain = orderedFoods.stream().filter(food -> food.getCategory() == Category.MAIN).count();
        long amountOfWeekendBenefit = 2023 * countOfMain;
        if (0 < amountOfWeekendBenefit) {
            totalBenefits.add("주말 할인: -" + amountOfWeekendBenefit + "원");
            amountOfTotalBenefits += amountOfWeekendBenefit;
        }
        return
    }

    record BenefitAmount(Optional<Integer> amountOfDDay,
                         Optional<Integer> amountOfWeekend,
                         Optional<Integer> amountOfWeekday,
                         Optional<Integer> amountOfSpecial) {

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
