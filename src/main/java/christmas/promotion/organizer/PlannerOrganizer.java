package christmas.promotion.organizer;

import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.organizer.viewer.OrderManager;
import christmas.promotion.organizer.viewer.PromotionPlanner;
import christmas.promotion.organizer.viewer.ReservationManager;

public class PlannerOrganizer {

    private final ReservationManager reservationManager;
    private final OrderManager orderManager;
    private final PromotionPlanner promotionPlanner;

    public PlannerOrganizer(ReservationManager reservationManager, OrderManager orderManager, PromotionPlanner promotionPlanner) {
        this.reservationManager = reservationManager;
        this.orderManager = orderManager;
        this.promotionPlanner = promotionPlanner;
    }

    public void run() {
        reservationManager.sayGreeting();
        Date reservationDate = reservationManager.askReservationDate();
        Orders orders = orderManager.takeOrders();
        promotionPlanner.announceBenefitPreview(reservationDate, orders);
    }

}