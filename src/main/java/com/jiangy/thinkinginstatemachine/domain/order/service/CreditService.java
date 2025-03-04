package com.jiangy.thinkinginstatemachine.domain.order.service;

import org.springframework.stereotype.Component;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class CreditService {
    public int checkCreditScore(Long buyerId) {
        return 800;
    }
}
