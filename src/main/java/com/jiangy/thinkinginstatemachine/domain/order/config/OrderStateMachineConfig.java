package com.jiangy.thinkinginstatemachine.domain.order.config;

import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import com.jiangy.thinkinginstatemachine.domain.order.state.OrderAction;
import com.jiangy.thinkinginstatemachine.domain.order.state.OrderGuard;
import com.jiangy.thinkinginstatemachine.domain.order.state.PaymentGuard;
import com.jiangy.thinkinginstatemachine.domain.order.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
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
@EnableStateMachineFactory
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    @Autowired
    private OrderGuard orderGuard;

    @Autowired
    private PaymentGuard paymentGuard;

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
                /* 预付费订单流程 */
                .withExternal()
                .source(OrderStates.INITIAL)
                .target(OrderStates.UN_PAID)
                .event(OrderEvents.PAYMENT_REQUIRED)
                .guard(paymentGuard.isPrepaid())
                .action(orderAction.handleUserOrder())


                /* 后付费订单流程 */
                .and()
                .withExternal()
                .source(OrderStates.INITIAL)
                .target(OrderStates.TO_DELIVER)
                .event(OrderEvents.USER_ORDERED)
                .guard(paymentGuard.isPostpaid())
                .action(orderAction.handleUserOrder())
        ;


    }

}
