package com.jiangy.thinkinginstatemachine.domain.order.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class PaymentService {
    public boolean checkFunds(Long buyerId, BigDecimal totalAmount) {
        return true;
    }
}
