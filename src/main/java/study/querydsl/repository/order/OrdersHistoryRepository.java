package study.querydsl.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.querydsl.entity.OrdersHistory;

public interface OrdersHistoryRepository extends JpaRepository<OrdersHistory, Long> {

}
