package christmas.promotion.organizer;

import christmas.promotion.collborator.menu.Food;
import christmas.promotion.collborator.menu.Menu;
import christmas.promotion.collborator.order.Order;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.collborator.promotioncalendar.BenefitAmount;
import christmas.promotion.collborator.promotioncalendar.Calendar;
import christmas.promotion.collborator.promotioncalendar.Date;
import christmas.promotion.collborator.promotioncalendar.Promotion;
import christmas.promotion.collborator.promotioncalendar.Promotions;
import christmas.promotion.enums.GlobalMessage;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlannerOrganizer {

    private final Output output;
    private final Input input;

    public PlannerOrganizer(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void run() {

        output.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        // TODO : 예외 시 재입력 처리를 적용한다
        output.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        Date reservationDate = Calendar.findDate(input.number());

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

        // TODO : if 블럭 안에서 최소 두 가지 일을 한 번에 하고 있다 - 1)할인문구 생성 2)할인총액 연산
        // TODO : List<Promotion> todaysPromotion를 유용하게 변경한 Promotions 클래스를 도입할 것
        // 여기를 어떻게 구조화하느냐가 관건이다. 섣부르게 설계하지 말 것.
        // 혜택 목록 만들기와 총 혜택금액 계산하는 부분 - '혜택'이란 개념에 포함되는 두 결과값을 래핑한 값객체로 만들면 좋겠다
        output.println("<혜택 내역>");
//        Promotions promotionsOfReservationDate = Calendar.findPromotionsBy(reservationDate, orders);
//        BenefitAmount benefits = promotionsOfReservationDate.askBenefitAmount();

        if (benefits.isAllEmpty()) {
            output.println("없음");
        }
        else {
            List<String> totalBenefitMessages = new ArrayList<>();
            if (benefits.amountOfDDay().isPresent()) {
                totalBenefitMessages.add("크리스마스 디데이 할인: -" + benefits.amountOfDDay().get() + "원");
            }
            if (benefits.amountOfWeekend().isPresent()) {
                totalBenefitMessages.add("주말 할인: -" + benefits.amountOfWeekend().get() + "원");
            }
            if (benefits.amountOfWeekday().isPresent()) {
                totalBenefitMessages.add("평일 할인: -" + benefits.amountOfWeekday().get() + "원");
            }
            if (benefits.amountOfSpecial().isPresent()) {
                totalBenefitMessages.add("특별 할인: -" + benefits.amountOfSpecial().get() + "원");
            }

        }
        List<String> totalBenefits = new ArrayList<>();
        int amountOfTotalBenefits = 0;
        if (10_000 <= orderedTotalPrice) {
            Promotions todaysPromotion = Calendar.findPromotionsBy(reservationDate, orderedTotalPrice);

            if (todaysPromotion.contains(Promotion.D_DAY)) {
                int amountOfDDayBenefit = (reservationDate - 1) * 100 + 1000;
                totalBenefits.add("크리스마스 디데이 할인: -" + amountOfDDayBenefit + "원");
                amountOfTotalBenefits += amountOfDDayBenefit;
            }
            if (todaysPromotion.contains(Promotion.WEEKEND)) {
                long countOfMain = orderedFoods.stream().filter(food -> food.getCategory() == Category.MAIN).count();
                long amountOfWeekendBenefit = 2023 * countOfMain;
                if (0 < amountOfWeekendBenefit) {
                    totalBenefits.add("주말 할인: -" + amountOfWeekendBenefit + "원");
                    amountOfTotalBenefits += amountOfWeekendBenefit;
                }
            }
            if (todaysPromotion.contains(Promotion.WEEKDAY)) {
                long countOfMain = orderedFoods.stream().filter(food -> food.getCategory() == Category.DESSERT).count();
                long amountOfWeekdayBenefit = 2023 * countOfMain;
                if (0 < amountOfWeekdayBenefit) {
                    totalBenefits.add("주말 할인: -" + amountOfWeekdayBenefit + "원");
                    amountOfTotalBenefits += amountOfWeekdayBenefit;
                }
            }
            if (todaysPromotion.contains(Promotion.SPECIAL)) {
                int amountOfSpecialBenefit = 1000;
                totalBenefits.add("특별 할인: -" + amountOfSpecialBenefit + "원");
                amountOfTotalBenefits += amountOfSpecialBenefit;
            }
            if (120_000 <= orderedTotalPrice) {
                int amountOfChampagne = menu.stream()
                        .filter(food -> food.getName().equals("샴페인"))
                        .map(Food::getPrice)
                        .findFirst()
                        .orElse(0);
                if (0 < amountOfChampagne) {
                    totalBenefits.add("증정 이벤트: -" + amountOfChampagne + "원");
                    amountOfTotalBenefits += amountOfChampagne;
                }
            }
        }
        String messageOfTotalBenefits = totalBenefits.stream().collect(Collectors.joining(System.lineSeparator()));
        if (totalBenefits.size() == 0) {
            messageOfTotalBenefits = "없음";
        }
        output.println(messageOfTotalBenefits);
        output.println();

        output.println("<총혜택 금액>");
        output.println(amountOfTotalBenefits + "원");
        output.println();

        output.println("<할인 후 예상 결제 금액>");
        int amountOfAfterDiscount = orderedTotalPrice - amountOfTotalBenefits;
        output.println(amountOfAfterDiscount + "원");
        output.println();

        output.println("<12월 이벤트 배지>");
        output.println(queryEventBadgeBy(amountOfTotalBenefits));
        output.println();
    }

    private String queryEventBadgeBy(int amountOfTotalBenefits) {
        if (20_000 <= amountOfTotalBenefits) {
            return "산타";
        }
        if (10_000 <= amountOfTotalBenefits) {
            return "트리";
        }
        if (5_000 <= amountOfTotalBenefits) {
            return "별";
        }
        return "없음";
    }

}

//class DecemberDate {
//
//    enum Promotion { D_DAY, WEEKDAY, WEEKEND, SPECIAL }
//    enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY }
//
//    private final int date;
//    private final Day day;
//    private final List<Promotion> promotions;
//
//    public DecemberDate(int date, Day day, List<Promotion> promotions) {
//        this.date = date;
//        this.day = day;
//        this.promotions = promotions;
//    }
//
//    public List<Promotion> getEvents() {
//        return promotions;
//    }
//
//}

//class Food {
//
//    enum Category { APPETIZER, MAIN, DESSERT, BEVERAGE }
//
//    private final Category category;
//    private final String name;
//    private final int price;
//
//    public Food(Category category, String name, int price) {
//        this.category = category;
//        this.name = name;
//        this.price = price;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Food food = (Food) o;
//        return name.equals(food.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
//
//}