package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import com.jiangy.thinkinginstatemachine.domain.order.entity.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component("postpaidStrategy")
public class PostpaidStrategy implements PaymentStrategy {

    @Override
    public void validate(Order order) {
//        CreditService creditService = new CreditService();
//        if (!creditService.checkCredit(order.getBuyerId(), order.getTotalAmount())) {
//            throw new PaymentException("信用额度不足");
//        }
    }

    @Override
    public Optional<PaymentResponse> process(Order order) {
        // 记录支付义务
//        paymentService.createPaymentObligation(order);
        order.setPaymentDeadline(LocalDateTime.now().plusDays(7));
        return Optional.empty();
    }

    @Override
    public void handleTimeout(Order order) {
//        collectionService.startCollectionProcess(order);
//        order.setStatus(OrderStates.PAYMENT_OVERDUE);
    }
}