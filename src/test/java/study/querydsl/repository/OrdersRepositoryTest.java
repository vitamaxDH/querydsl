package study.querydsl.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.LatestOrderDto;
import study.querydsl.entity.Orders;
import study.querydsl.repository.order.OrdersHistoryRepository;
import study.querydsl.repository.order.OrdersService;
import study.querydsl.type.OrderStatus;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class OrdersRepositoryTest {

    @Autowired
    private OrdersService ordersService;

    @Test
    public void scalaSubQueryLimitTest() {
        Orders orders = Orders.from("맥북 프로");
        ordersService.save(orders);

        ordersService.changeOrderStatus(orders, OrderStatus.READY, LocalDateTime.of(2022, 3, 5, 9, 0, 0));
        ordersService.changeOrderStatus(orders, OrderStatus.SHIPPING, LocalDateTime.of(2022, 3, 6, 9, 0, 0));
        ordersService.changeOrderStatus(orders, OrderStatus.DELIVERED, LocalDateTime.of(2022, 3, 7, 9, 0, 0));

        Exception e = Assertions.assertThrows(Exception.class, () -> {
            ordersService.findLatestOrderHistoryByLimit(orders.getId());
        });

        System.out.println("Exception message: " + e.getMessage());

        LatestOrderDto latestOrderHistoryByWhereSubQuery = ordersService.findLatestOrderHistoryByWhereSubQuery(orders.getId());

        System.out.println(latestOrderHistoryByWhereSubQuery);
    }

}
