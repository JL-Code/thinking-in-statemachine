package com.jiangy.thinkinginstatemachine.domain.order.enums;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public enum OrderEvents {
    USER_ORDERED,
    SELLER_SHIPPED,
    BUYER_PAUSED,
    SYSTEM_TIMEOUT,
    BUYER_CANCELED,
    SELLER_CANCELED,
    PLATFORM_CANCELED,
    BUYER_CONFIRMED_RECEIPT,
    BUYER_RAISED_DISPUTE,
    BUYER_SETTLED,
    PAYMENT_CONFIRMED,
    PAYMENT_REQUIRED
}
