package christmas.promotion.organizer;

import christmas.promotion.collborator.order.Orders;
import christmas.promotion.collborator.calendar.BenefitAmount;
import christmas.promotion.collborator.calendar.Calendar;
import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.calendar.PromotionBadge;
import christmas.promotion.collborator.calendar.Promotions;
import christmas.promotion.enums.GlobalMessage;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;
import christmas.promotion.organizer.viewer.OrderManager;
import christmas.promotion.organizer.viewer.ReservationManager;
import java.util.ArrayList;
import java.util.List;

public class PlannerOrganizer {

    private final Output output;
    private final Input input;
    private final ReservationManager reservationManager;
    private final OrderManager orderManager;

    public PlannerOrganizer(Input input, Output output, ReservationManager reservationManager, OrderManager orderManager) {
        this.input = input;
        this.output = output;
        this.reservationManager = reservationManager;
        this.orderManager = orderManager;
    }

    public void run() {

        reservationManager.sayGreeting();

        Date reservationDate = reservationManager.askReservationDate();

        Orders orders = orderManager.takeOrders();

        reservationManager.announceBenefitPreview(reservationDate, orders);

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