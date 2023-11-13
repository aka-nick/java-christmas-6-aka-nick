package christmas.promotion.organizer;

import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.collborator.calendar.BenefitAmount;
import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.calendar.PromotionBadge;
import christmas.promotion.collborator.calendar.Promotions;
import christmas.promotion.enums.GlobalMessage;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;
import christmas.promotion.organizer.viewer.ReservationManager;
import java.util.ArrayList;
import java.util.List;

public class PlannerOrganizer {

    private final Output output;
    private final Input input;
    private final ReservationManager reservationManager;

    public PlannerOrganizer(Input input, Output output, ReservationManager reservationManager) {
        this.input = input;
        this.output = output;
        this.reservationManager = reservationManager;
    }

    public void run() {

        reservationManager.greetToGuest();

        Date reservationDate = reservationManager.askReservationDate();

        // TODO : 예외 시 재입력 처리를 적용한다
        output.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        List<String> reservations = input.strings(",");
        Orders orders = new Orders(reservations.stream()
                .map(reservation -> reservation.split("-"))
                .map(Order::place)
                .toList());

        output.println("12월 " + reservationDate.date() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        output.println("<주문 메뉴>");
        List<String> orderedFoods = orders.findAllOrderedFood();
        output.println(String.join(GlobalMessage.NEW_LINE.get(), orderedFoods));
        output.println();

        output.println("<할인 전 총주문 금액>");
        int orderedPrice = orders.calculateTotalPrice();
        output.println(orderedPrice);
        output.println();

        Promotions promotionsOfReservationDate = Calendar.findPromotionsBy(reservationDate, orders);
        BenefitAmount benefits = promotionsOfReservationDate.askBenefitAmount();

        output.println("<증정 메뉴>");
        output.println(benefits.askResultOfGiveaway());
        output.println();

        output.println("<혜택 내역>");

        // TODO : 아래의 if-else문은 전용view로 이동하며 제거될 듯함.
        if (benefits.isAllDiscountEmpty()) {
            output.println("없음");
        }
        else {
            List<String> benefitMessages = new ArrayList<>();
            if (benefits.amountOfDDay().isPresent()) {
                benefitMessages.add("크리스마스 디데이 할인: -" + benefits.amountOfDDay().get() + "원");
            }
            if (benefits.amountOfWeekend().isPresent()) {
                benefitMessages.add("주말 할인: -" + benefits.amountOfWeekend().get() + "원");
            }
            if (benefits.amountOfWeekday().isPresent()) {
                benefitMessages.add("평일 할인: -" + benefits.amountOfWeekday().get() + "원");
            }
            if (benefits.amountOfSpecial().isPresent()) {
                benefitMessages.add("특별 할인: -" + benefits.amountOfSpecial().get() + "원");
            }
            if (benefits.amountOfGiveaway().isPresent()) {
                benefitMessages.add("증정 이벤트: -" + benefits.amountOfGiveaway().get() + "원");
            }
            output.println(String.join(GlobalMessage.NEW_LINE.get(), benefitMessages));
        }
        output.println();

        output.println("<총혜택 금액>");
        output.println("-" + benefits.amountOfTotalBenefits() + "원");
        output.println();

        output.println("<할인 후 예상 결제 금액>");
        output.println((orders.calculateTotalPrice() - benefits.amountOfTotalBenefits()) + "원");
        output.println();

        output.println("<12월 이벤트 배지>");
        output.println(PromotionBadge.findPromotionBadgeBy(benefits.amountOfTotalBenefits()));
        output.println();
    }

}