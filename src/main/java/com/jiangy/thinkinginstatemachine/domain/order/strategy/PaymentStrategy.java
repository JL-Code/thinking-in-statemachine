package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import com.jiangy.thinkinginstatemachine.domain.order.entity.Order;
import com.jiangy.thinkinginstatemachine.domain.order.exception.PaymentException;

import java.util.Map;
import java.util.Optional;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public interface PaymentStrategy {
    /**
     * 校验支付参数
     *
     * @param order 订单信息
     * @throws PaymentException 支付异常
     */
    void validate(Order order) throws PaymentException;

    /**
     * 调用第三方支付网关创建交易
     *
     * @param order 订单信息
     * @throws PaymentException 支付异常
     */
    Optional<PaymentResponse> process(Order order) throws PaymentException;

    /**
     * 处理支付回调
     *
     * @param callbackParams 回调参数
     * @throws PaymentException 支付异常
     */
    default void handleCallback(Map<String, Object> callbackParams) throws PaymentException {
    }

    /**
     * 支付超时处理
     * 【可选】调用支付平台关单接口
     *
     * @param order 订单信息
     */
    void handleTimeout(Order order);
}