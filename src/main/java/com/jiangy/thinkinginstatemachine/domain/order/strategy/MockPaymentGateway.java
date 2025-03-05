package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * <p>创建时间: 2025/3/5 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Slf4j
@Component
public class MockPaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(BigDecimal totalAmount) {
        return new PaymentResult().setTransactionId(UUID.randomUUID().toString());
    }

    @Override
    public void cancel(String paymentTransactionId) {
        // 取消订单
        log.info("Payment cancelled paymentTransactionId: {}", paymentTransactionId);
    }
}
