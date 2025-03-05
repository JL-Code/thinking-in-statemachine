package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Data
@Accessors(chain = true)
public class PaymentResult {
    private String transactionId;
}
