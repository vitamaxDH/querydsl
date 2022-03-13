package study.querydsl.repository.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.LatestOrderDto;
import study.querydsl.entity.Orders;
import study.querydsl.entity.OrdersHistory;
import study.querydsl.type.OrderStatus;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersHistoryRepository ordersHistoryRepository;

    public Orders save(Orders orders){
        return ordersRepository.save(orders);
    }

    public void changeOrderStatus(Orders orders, OrderStatus orderStatus, LocalDateTime localDateTime){
        orders.changeStatus(orderStatus);
        OrdersHistory ordersHistory = OrdersHistory.of(orders, orderStatus, localDateTime);
        ordersHistoryRepository.save(ordersHistory);
    }

    public LatestOrderDto findLatestOrderHistoryByLimit(Long orderId){
        return ordersRepository.findLatestOrderHistoryByLimit(orderId);
    }

    public LatestOrderDto findLatestOrderHistoryByWhereSubQuery(Long orderId){
        return ordersRepository.findLatestOrderHistoryByWhereSubQuery(orderId);
    }

}
