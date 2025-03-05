package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import com.jiangy.thinkinginstatemachine.domain.order.entity.Order;
import com.jiangy.thinkinginstatemachine.domain.order.exception.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component("prepaidStrategy")
public class PrepaidStrategy implements PaymentStrategy {

    @Autowired
    private PaymentGateway paymentGateway;

    @Override
    public void validate(Order order) {
        if (order.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentException("预付款金额必须大于0");
        }
        // 可防止订单重复支付，支付过期后不允许再支付。
        if (order.getPaymentDeadline().isBefore(LocalDateTime.now().plusMinutes(15))) {
            throw new PaymentException("支付有效期过短");
        }
    }

    @Override
    public Optional<PaymentResponse> process(Order order) {
        // 调用支付网关
        PaymentResult result = paymentGateway.charge(order.getTotalAmount());

        // mock
        return Optional.of(new PaymentResponse(
                "http://localhost:8081/api/payment/callback",
                null));
    }

    @Override
    public void handleCallback(Map<String, Object> callbackParams) throws PaymentException {
        // 使用具体支付渠道来解析 callbackParams
    }

    @Override
    public void handleTimeout(Order order) {
        paymentGateway.cancel(order.getPaymentTransactionId());
        // 调用支付平台关单接口
    }
}