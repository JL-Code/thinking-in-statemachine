package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import com.jiangy.thinkinginstatemachine.domain.order.Order;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.exception.PaymentException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component("prepaidStrategy")
public class PrepaidStrategy implements PaymentStrategy {


    private PaymentGateway paymentGateway;

    @Override
    public void validate(Order order) {
        if (order.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentException("预付款金额必须大于0");
        }
    }

    @Override
    public void process(Order order) {
        // 调用支付网关
        PaymentResult result = paymentGateway.charge(order.getTotalAmount());
        order.setPaymentTransactionId(result.getTransactionId());
        order.setStatus(OrderStates.TO_DELIVER);
    }

    @Override
    public void handleTimeout(Order order) {
        paymentGateway.cancel(order.getPaymentTransactionId());
        order.setStatus(OrderStates.CANCELED_TIMEOUT);
    }
}