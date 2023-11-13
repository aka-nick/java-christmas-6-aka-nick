package christmas.promotion.collborator.menu;

import christmas.promotion.collborator.menu.Food.Category;
import java.util.HashMap;
import java.util.Map;

public class Menu {

    private static final Map<String, Food> menu = new HashMap<>();

    static {
        menu.put("양송이수프", new Food(Category.APPETIZER, "양송이수프", 6_000));
        menu.put("타파스", new Food(Category.APPETIZER, "타파스", 5_500));
        menu.put("시저샐러드", new Food(Category.APPETIZER, "시저샐러드", 8_000));
        menu.put("티본스테이크", new Food(Category.MAIN, "티본스테이크", 55_000));
        menu.put("바비큐립", new Food(Category.MAIN, "바비큐립", 54_000));
        menu.put("해산물파스타", new Food(Category.MAIN, "해산물파스타", 35_000));
        menu.put("크리스마스파스타", new Food(Category.MAIN, "크리스마스파스타", 25_000));
        menu.put("초코케이크", new Food(Category.DESSERT, "초코케이크", 15_000));
        menu.put("아이스크림", new Food(Category.DESSERT, "아이스크림", 5_000));
        menu.put("제로콜라", new Food(Category.BEVERAGE, "제로콜라", 3_000));
        menu.put("레드와인", new Food(Category.BEVERAGE, "레드와인", 60_000));
        menu.put("샴페인", new Food(Category.BEVERAGE, "샴페인", 25_000));
    }

    public static Food findBy(String foodName) {
        try {
            return menu.get(foodName);
        } catch (ClassCastException | NullPointerException e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.", e);
        }
    }

}
