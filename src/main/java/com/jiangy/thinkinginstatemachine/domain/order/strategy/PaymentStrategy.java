package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import com.jiangy.thinkinginstatemachine.domain.order.Order;
import com.jiangy.thinkinginstatemachine.domain.order.exception.PaymentException;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public interface PaymentStrategy {
    /**
     * 支付前校验
     *
     * @param order 订单信息
     * @throws PaymentException 支付异常
     */
    void validate(Order order) throws PaymentException;

    void process(Order order) throws PaymentException;

    void handleTimeout(Order order);
}