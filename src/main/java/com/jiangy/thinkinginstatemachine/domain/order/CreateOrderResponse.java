package com.jiangy.thinkinginstatemachine.domain.order;

import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import com.jiangy.thinkinginstatemachine.domain.order.strategy.PaymentResponse;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>创建时间: 2025/3/5 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Data
@Accessors(chain = true)
public class CreateOrderResponse {
    private String orderNo;
    private PaymentTypes paymentType;
    private PaymentResponse paymentResponse;
}
