package study.querydsl.entity;

import lombok.Getter;
import lombok.ToString;
import study.querydsl.type.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Entity
public class OrdersHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Orders orders;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column
    private LocalDateTime createdAt;

    public OrdersHistory setOrders(Orders orders) {
        this.orders = orders;
        return this;
    }

    public static OrdersHistory of(Orders orders, OrderStatus orderStatus, LocalDateTime createdAt) {
        OrdersHistory ordersHistory = new OrdersHistory();
        ordersHistory.orders = orders;
        ordersHistory.orderStatus = orderStatus;
        ordersHistory.createdAt = createdAt;
        return ordersHistory;
    }
}
