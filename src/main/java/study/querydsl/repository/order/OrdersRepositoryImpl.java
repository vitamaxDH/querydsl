package study.querydsl.repository.order;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import study.querydsl.dto.LatestOrderDto;
import study.querydsl.entity.Orders;

import static study.querydsl.entity.QOrders.orders;
import static study.querydsl.entity.QOrdersHistory.ordersHistory;

public class OrdersRepositoryImpl extends QuerydslRepositorySupport implements OrdersCustomRepository {

    public OrdersRepositoryImpl() {
        super(Orders.class);
    }

    @Override
    public LatestOrderDto findLatestOrderHistoryByLimit(Long orderId) {
        return from(orders)
                .innerJoin(ordersHistory)
                .on(ordersHistory.orders.eq(orders))
                .where(orders.id.eq(orderId))
                .select(
                        Projections.constructor(LatestOrderDto.class,
                                orders,
                                Expressions.as(
                                        from(ordersHistory)
                                                .orderBy(ordersHistory.createdAt.desc())
                                                .limit(1)
                                        , "ordersHistory")
                        )
                )
                .fetchFirst();
    }

    @Override
    public LatestOrderDto findLatestOrderHistoryByWhereSubQuery(Long orderId) {
        return from(orders)
                .innerJoin(ordersHistory)
                .on(ordersHistory.orders.eq(orders))
                .where(orders.id.eq(orderId))
                .select(
                        Projections.constructor(LatestOrderDto.class,
                                orders,
                                Expressions.as(
                                        from(ordersHistory)
                                                .where(
                                                        ordersHistory.id.eq(
                                                                JPAExpressions
                                                                        .select(ordersHistory.id.max())
                                                                        .from(ordersHistory)
                                                                        .where(ordersHistory.orders.id.eq(orderId))
                                                        )
                                                )
                                        , "ordersHistory")
                        )
                )
                .fetchFirst();
    }

}
