package christmas.promotion.organizer;

import christmas.promotion.organizer.DecemberDate.Day;
import christmas.promotion.organizer.DecemberDate.Event;
import christmas.promotion.organizer.Food.Category;
import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlannerOrganizer {

    private final Output output;
    private final Input input;

    public PlannerOrganizer(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        List<DecemberDate> calendar = new ArrayList<>();
        calendar.add(new DecemberDate(0, Day.FRIDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(1, Day.FRIDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(2, Day.SATURDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(3, Day.SUNDAY, List.of(Event.D_DAY, Event.WEEKDAY, Event.SPECIAL)));
        calendar.add(new DecemberDate(4, Day.MONDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(5, Day.TUESDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(6, Day.WEDNESDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(7, Day.THURSDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(8, Day.FRIDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(9, Day.SATURDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(10, Day.SUNDAY, List.of(Event.D_DAY, Event.WEEKDAY, Event.SPECIAL)));
        calendar.add(new DecemberDate(11, Day.MONDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(12, Day.TUESDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(13, Day.WEDNESDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(14, Day.THURSDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(15, Day.FRIDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(16, Day.SATURDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(17, Day.SUNDAY, List.of(Event.D_DAY, Event.WEEKDAY, Event.SPECIAL)));
        calendar.add(new DecemberDate(18, Day.MONDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(19, Day.TUESDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(20, Day.WEDNESDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(21, Day.THURSDAY, List.of(Event.D_DAY, Event.WEEKDAY)));
        calendar.add(new DecemberDate(22, Day.FRIDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(23, Day.SATURDAY, List.of(Event.D_DAY, Event.WEEKEND)));
        calendar.add(new DecemberDate(24, Day.SUNDAY, List.of(Event.D_DAY, Event.WEEKDAY, Event.SPECIAL)));
        calendar.add(new DecemberDate(25, Day.MONDAY, List.of(Event.D_DAY, Event.WEEKDAY, Event.SPECIAL)));
        calendar.add(new DecemberDate(26, Day.TUESDAY, List.of(Event.WEEKDAY)));
        calendar.add(new DecemberDate(27, Day.WEDNESDAY, List.of(Event.WEEKDAY)));
        calendar.add(new DecemberDate(28, Day.THURSDAY, List.of(Event.WEEKDAY)));
        calendar.add(new DecemberDate(29, Day.FRIDAY, List.of(Event.WEEKEND)));
        calendar.add(new DecemberDate(30, Day.SATURDAY, List.of(Event.WEEKEND)));
        calendar.add(new DecemberDate(31, Day.SUNDAY, List.of(Event.WEEKDAY, Event.SPECIAL)));


        List<Food> menu = new ArrayList<>();
        menu.add(new Food(Category.APPETIZER, "양송이수프", 6_000));
        menu.add(new Food(Category.APPETIZER, "타파스", 5_500));
        menu.add(new Food(Category.APPETIZER, "시저샐러드", 8_000));
        menu.add(new Food(Category.MAIN, "티본스테이크", 55_000));
        menu.add(new Food(Category.MAIN, "바비큐립", 54_000));
        menu.add(new Food(Category.MAIN, "해산물파스타", 35_000));
        menu.add(new Food(Category.MAIN, "크리스마스파스타", 25_000));
        menu.add(new Food(Category.DESSERT, "초코케이크", 15_000));
        menu.add(new Food(Category.DESSERT, "아이스크림", 5_000));
        menu.add(new Food(Category.BEVERAGE, "제로콜라", 3_000));
        menu.add(new Food(Category.BEVERAGE, "레드와인", 60_000));
        menu.add(new Food(Category.BEVERAGE, "샴페인", 25_000));

        output.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        output.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int reservationDate = input.number();

        output.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        List<String> reservations = input.strings(",");
        List<OrderFood> orderMenus = reservations.stream()
                .map(reservation -> reservation.split("-"))
                .map(food -> new OrderFood(food[0], Integer.parseInt(food[1])))
                .toList();

        output.println("12월 " + reservationDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        // 아래의 orderedFoods가 나오는 스트림 연산을 먼저 해놓고, getName으로 메뉴명만 쭉 뽑는 방식이 더 좋을 것 같다
        output.println("<주문 메뉴>");
        String orderedMenu = orderMenus.stream()
                .map(orderFood -> orderFood.getFood().getName() + " " + orderFood.getAmount())
                .collect(Collectors.joining("\n"));
        output.println(orderedMenu);

        output.println("<할인 전 총주문 금액>");
        // 리스트로 메뉴를 저장했다보니까 메뉴를 순회하면서 찾아야 하는데 이름을 키로 하는 맵으로 하면 더 좋을 거 같다.
        List<Food> orderedFoods = orderMenus.stream()
                .map(orderFood ->
                        menu.stream()
                                .filter(menuFood -> menuFood.getName().equals(orderFood.getFood().getName()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.")))
                .toList();
        int orderedtotalPrice = orderedFoods.stream()
                .mapToInt(Food::getPrice)
                .sum();
        output.println(orderedtotalPrice);

        output.println("<증정 메뉴>");
        String resultOfGiveaway = "없음";
        int amountOfGiveaway = 0;
        if (120_000 <= orderedtotalPrice) {
            resultOfGiveaway = "샴페인 1개";
            // 여기도, 맵으로 변경하면 순회할 필요 사라짐
            amountOfGiveaway = menu.stream().filter(food -> food.getName().equals("샴페인")).mapToInt(Food::getPrice).findFirst().getAsInt();
        }
        output.println(resultOfGiveaway);

        // 혜택 목록 만들기와 총 혜택금액 계산하는 부분 - '혜택'이란 개념에 포함되는 두 결과값을 래핑한 값객체로 만들면 좋겠다
        output.println("<혜택 내역>");
        List<String> totalBenefits = new ArrayList<>();
        int amountOfTotalBenefits = 0;
        if (10_000 <= orderedtotalPrice) {
            List<Event> eventsOfReservationDate = calendar.get(reservationDate).getEvents();
            if (eventsOfReservationDate.contains(Event.D_DAY)) {
                int amountOfDDayBenefit = (reservationDate - 1) * 100 + 1000;
                totalBenefits.add("크리스마스 디데이 할인: -" + amountOfDDayBenefit + "원");
                amountOfTotalBenefits += amountOfDDayBenefit;
            }
            if (eventsOfReservationDate.contains(Event.WEEKEND)) {
                long countOfMain = orderedFoods.stream().filter(food -> food.getCategory() == Category.MAIN).count();
                long amountOfWeekendBenefit = 2023 * countOfMain;
                if (0 < amountOfWeekendBenefit) {
                    totalBenefits.add("주말 할인: -" + amountOfWeekendBenefit + "원");
                    amountOfTotalBenefits += amountOfWeekendBenefit;
                }
            }
            if (eventsOfReservationDate.contains(Event.WEEKDAY)) {
                long countOfMain = orderedFoods.stream().filter(food -> food.getCategory() == Category.DESSERT).count();
                long amountOfWeekdayBenefit = 2023 * countOfMain;
                if (0 < amountOfWeekdayBenefit) {
                    totalBenefits.add("주말 할인: -" + amountOfWeekdayBenefit + "원");
                    amountOfTotalBenefits += amountOfWeekdayBenefit;
                }
            }
            if (eventsOfReservationDate.contains(Event.SPECIAL)) {
                int amountOfSpecialBenefit = 1000;
                totalBenefits.add("특별 할인: -" + amountOfSpecialBenefit + "원");
                amountOfTotalBenefits += amountOfSpecialBenefit;
            }
            if (120_000 <= orderedtotalPrice) {
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
            output.println(
                    totalBenefits.stream().collect(Collectors.joining(System.lineSeparator())));


        }

    }

//    private static String applyDiscountBenefits(int orderedTotalPrice, List<DecemberDate> calendar, int reservationDate) {
//        if (orderedTotalPrice < 10000) {
//            return "없음";
//        }
//
//        List<String> appliedBenefits = new ArrayList<>();
//        DecemberDate decemberDate = calendar.get(reservationDate);
//        boolean whetherApplyDDay = decemberDate.getEvents().stream()
//                .anyMatch(event -> event == Event.D_DAY);
//        boolean whetherApplyWeekend = decemberDate.getEvents().stream()
//                .anyMatch(event -> event == Event.WEEKEND);
//        boolean whetherApplyWeekday = decemberDate.getEvents().stream()
//                .anyMatch(event -> event == Event.WEEKDAY);
//        boolean whetherApplySpecial = decemberDate.getEvents().stream()
//                .anyMatch(event -> event == Event.SPECIAL);
//
//        if (whetherApplyDDay) {
//            appliedBenefits.add("크리스마스 디데이 할인: -" + (reservationDate - 1) * 100 + 1000 + "원");
//        }
//        if (whetherApplyWeekend) {
//            appliedBenefits.add("주말 할인: -" + 2023 + )
//        }
//    }

}

class DecemberDate {

    enum Event { D_DAY, WEEKDAY, WEEKEND, SPECIAL }
    enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY }

    private final int date;
    private final Day day;
    private final List<Event> events;

    public DecemberDate(int date, Day day, List<Event> events) {
        this.date = date;
        this.day = day;
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

}

class Food {

    enum Category { APPETIZER, MAIN, DESSERT, BEVERAGE }

    private final Category category;
    private final String name;
    private final int price;

    public Food(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

class OrderFood {

    private final Food food;
    private final int amount;

    public OrderFood(Food food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    public Food getFood() {
        return food;
    }

    public int getAmount() {
        return amount;
    }

}