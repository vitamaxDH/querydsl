package study.querydsl.repository.order;

import study.querydsl.dto.LatestOrderDto;

public interface OrdersCustomRepository {

    LatestOrderDto findLatestOrderHistoryByLimit(Long orderId);

    LatestOrderDto findLatestOrderHistoryByWhereSubQuery(Long orderId);
}
