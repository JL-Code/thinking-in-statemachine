package com.jiangy.thinkinginstatemachine.domain.order.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>创建时间: 2025/3/5 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class PaymentResponse {
    /**
     * 支付链接
     */
    private String paymentUrl;
    /**
     * 支付二维码
     */
    private String qrCode;
}
