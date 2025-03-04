package com.jiangy.thinkinginstatemachine.domain.order;

import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import lombok.Data;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Data
public class OrderRequest {
    private PaymentTypes paymentType;
}
