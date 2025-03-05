package com.jiangy.thinkinginstatemachine.domain.order.enums;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public enum OrderStates {
    INITIAL,
    UN_PAID,
    TO_DELIVER,
    DELIVERED,
    PAUSED,
    RECEIVED,
    IN_DISPUTE,
    COMPLETED,
    CANCELED_TIMEOUT,
    CANCELED_PUNITIVE,
    CANCELED_PLATFORM,
    CANCELED_BUYER,
    CANCELED_SELLER
}
