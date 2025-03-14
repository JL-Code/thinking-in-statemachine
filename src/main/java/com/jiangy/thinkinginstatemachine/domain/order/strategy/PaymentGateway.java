package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import java.math.BigDecimal;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public interface PaymentGateway {
    /**
     * 创建交易
     *
     * @param totalAmount
     * @return
     */
    PaymentResult charge(BigDecimal totalAmount);

    /**
     * 关闭交易
     *
     * @param paymentTransactionId
     */
    void cancel(String paymentTransactionId);
}
