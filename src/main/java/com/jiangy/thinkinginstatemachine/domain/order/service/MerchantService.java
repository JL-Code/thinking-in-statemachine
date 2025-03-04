package com.jiangy.thinkinginstatemachine.domain.order.service;

import org.springframework.stereotype.Service;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Service
public class MerchantService {
    public boolean isEnabled(Long merchantId) {
        return merchantId >= 1000;
    }

    public boolean isRealNameVerified(Long merchantId) {
        return merchantId >= 1000;
    }

    public boolean isBlacklisted(Long merchantId, Long buyerId) {
        return merchantId >= 1000;
    }
}
