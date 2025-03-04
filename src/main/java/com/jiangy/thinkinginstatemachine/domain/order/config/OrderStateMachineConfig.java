package com.jiangy.thinkinginstatemachine.domain.order.config;

import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import com.jiangy.thinkinginstatemachine.domain.order.state.OrderAction;
import com.jiangy.thinkinginstatemachine.domain.order.state.OrderGuard;
import com.jiangy.thinkinginstatemachine.domain.order.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    @Autowired
    private OrderGuard orderGuard;

    @Autowired
    private OrderAction orderAction;

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
        states
                .withStates()
                .initial(OrderStates.INITIAL)
                .states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
        transitions
                // 后付费订单 用户下单
                .withExternal()
                .source(OrderStates.INITIAL)
                .target(OrderStates.TO_DELIVER)
                .event(OrderEvents.USER_ORDERED)
                .guard(isPostpaidOrder())
                .guard(orderGuard.userOrderGuard())
                .action(orderAction.handleUserOrder())

                // 卖家发货
                .and()
                .withExternal()
                .source(OrderStates.TO_DELIVER)
                .target(OrderStates.DELIVERED)
                .event(OrderEvents.SELLER_SHIPPED)
                .action(orderAction.handleSellerShipped())

                // 买家暂停订单
                .and()
                .withExternal()
                .source(OrderStates.TO_DELIVER)
                .target(OrderStates.PAUSED)
                .event(OrderEvents.BUYER_PAUSED)

                // 超时取消（含守卫条件）
                .and()
                .withExternal()
                .source(OrderStates.TO_DELIVER)
                .target(OrderStates.CANCELED_TIMEOUT)
                .event(OrderEvents.SYSTEM_TIMEOUT)
                .guard(orderGuard.timeoutCancelGuard())
                .action(orderAction.handleTimeoutCancel())

        // 其他转换规则...
        ;
    }

    private Guard<OrderStates, OrderEvents> isPrepaidOrder() {
        return context -> {
            var result = OrderUtils.getOrder(context);
            return result.filter(order -> order.getPaymentType() == PaymentTypes.PREPAID).isPresent();
        };
    }

    private Guard<OrderStates, OrderEvents> isPostpaidOrder() {
        return context -> {
            var result = OrderUtils.getOrder(context);
            return result.filter(order -> order.getPaymentType() == PaymentTypes.POSTPAID).isPresent();
        };
    }
}
