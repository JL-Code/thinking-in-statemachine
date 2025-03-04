package com.jiangy.thinkinginstatemachine.domain.order.utils;

import com.jiangy.thinkinginstatemachine.domain.order.Order;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import org.springframework.statemachine.StateContext;

import java.util.Optional;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class OrderUtils {
    public static Optional<Order> getOrder(StateContext<OrderStates, OrderEvents> context) {
        Order order = context.getMessage().getHeaders().get("order", Order.class);
        return Optional.ofNullable(order);
    }
}
