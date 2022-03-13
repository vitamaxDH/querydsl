package study.querydsl.entity;

import lombok.Getter;
import lombok.ToString;
import study.querydsl.type.OrderStatus;

import javax.persistence.*;

@ToString
@Getter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Orders from(String name){
        Orders orders = new Orders();
        orders.name = name;
        orders.orderStatus = OrderStatus.READY;
        return orders;
    }

    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
