package christmas.promotion.organizer;

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
                .map(orderFood -> orderFood.getName() + " " + orderFood.getAmount())
                .collect(Collectors.joining("\n"));
        output.println(orderedMenu);

        output.println("<할인 전 총주문 금액>");
        // 리스트로 메뉴를 저장했다보니까 메뉴를 순회하면서 찾아야 하는데 이름을 키로 하는 맵으로 하면 더 좋을 거 같다.
        List<Food> orderedFoods = orderMenus.stream()
                .map(orderFood ->
                        menu.stream()
                                .filter(menuFood -> menuFood.getName().equals(orderFood.getName()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.")))
                .toList();
        int orderedtotalPrice = orderedFoods.stream()
                .mapToInt(Food::getPrice)
                .sum();
        output.println(orderedtotalPrice);
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

    private final String name;
    private final int amount;

    public OrderFood(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

}