package christmas.promotion.organizer;

import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;
import java.util.List;

public class PlannerOrganizer {

    private final Output output;
    private final Input input;

    public PlannerOrganizer(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        output.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        output.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int reservationDate = input.number();

        output.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        List<String> reservations = input.strings(",");
        List<Food> orderMenus = reservations.stream()
                .map(reservation -> reservation.split("-"))
                .map(food -> new Food(food[0], Integer.parseInt(food[1])))
                .toList();

        output.println("12월 " + reservationDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

}
class Food {
    String name;
    int price;

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

}