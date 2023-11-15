package christmas.promotion.organizer;

import christmas.promotion.collborator.calendar.Date;
import christmas.promotion.collborator.order.Orders;
import christmas.promotion.organizer.manager.OrderManager;
import christmas.promotion.organizer.manager.PromotionManager;
import christmas.promotion.organizer.manager.ReservationManager;

public class PlannerOrganizer {

    private final ReservationManager reservationManager;
    private final OrderManager orderManager;
    private final PromotionManager promotionManager;

    public PlannerOrganizer(ReservationManager reservationManager, OrderManager orderManager, PromotionManager promotionManager) {
        this.reservationManager = reservationManager;
        this.orderManager = orderManager;
        this.promotionManager = promotionManager;
    }

    public void run() {
        reservationManager.sayGreeting();
        Date reservationDate = reservationManager.getReservation();
        Orders orders = orderManager.takeOrders();
        promotionManager.announceBenefitPreview(reservationDate, orders);
    }

}