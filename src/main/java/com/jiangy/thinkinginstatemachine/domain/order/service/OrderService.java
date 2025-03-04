package com.jiangy.thinkinginstatemachine.domain.order.service;

import com.jiangy.thinkinginstatemachine.domain.order.Order;
import com.jiangy.thinkinginstatemachine.domain.order.OrderRequest;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import com.jiangy.thinkinginstatemachine.domain.order.repository.OrderRepository;
import com.jiangy.thinkinginstatemachine.domain.order.strategy.PaymentStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    private StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory;

    @Autowired
    private Map<String, PaymentStrategy> paymentStrategies;

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setPaymentType(request.getPaymentType());

        // 选择支付策略
        PaymentStrategy strategy = paymentStrategies.get(
                request.getPaymentType().name().toLowerCase() + "Strategy"
        );

        strategy.validate(order);

        StateMachine<OrderStates, OrderEvents> sm = stateMachineFactory.getStateMachine(order.getOrderNo());
        sm.getExtendedState().getVariables().put("order", order);

        // 触发初始事件
        if (request.getPaymentType() == PaymentTypes.PREPAID) {
            sm.sendEvent(OrderEvents.PAYMENT_REQUIRED);
        } else {
            sm.sendEvent(OrderEvents.USER_ORDERED);
        }

        return orderRepository.save(order);
    }

    public void processPayment(String orderNo) {
        Order order = orderRepository.findByOrderNo(orderNo).orElseThrow();
        PaymentStrategy strategy = paymentStrategies.get(
                order.getPaymentType().name().toLowerCase() + "Strategy"
        );

        strategy.process(order);
        StateMachine<OrderStates, OrderEvents> sm = stateMachineFactory.getStateMachine(orderNo);
        sm.sendEvent(OrderEvents.PAYMENT_CONFIRMED);
    }
}