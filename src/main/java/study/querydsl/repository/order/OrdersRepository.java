package study.querydsl.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>, OrdersCustomRepository {
}
