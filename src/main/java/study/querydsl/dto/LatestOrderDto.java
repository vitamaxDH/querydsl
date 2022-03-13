package study.querydsl.dto;

import lombok.ToString;
import study.querydsl.entity.OrdersHistory;
import study.querydsl.entity.Orders;

@ToString
public class LatestOrderDto {

    private Orders orders;

    private OrdersHistory ordersHistory;

    public LatestOrderDto(Orders orders, OrdersHistory ordersHistory) {
        this.orders = orders;
        this.ordersHistory = ordersHistory;
    }

}
