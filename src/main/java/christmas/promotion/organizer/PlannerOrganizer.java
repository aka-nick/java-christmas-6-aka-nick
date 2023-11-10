package christmas.promotion.organizer;

import christmas.promotion.organizer.io.Input;
import christmas.promotion.organizer.io.Output;

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

    }

}
