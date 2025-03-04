package com.jiangy.thinkinginstatemachine.domain.order;

import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Data
@Entity(name = "t_order")
public class Order {
    @Id
    private Long id;
    private String orderNo;

    @Convert(converter = OrderStatesConverter.class)
    private OrderStates status;

    @Convert(converter = PaymentTypesConverter.class)
    private PaymentTypes paymentType;

    private Long merchantId;
    private Long buyerId;
    private Integer productId;
    private String paymentTransactionId;
    private BigDecimal totalAmount;
    private Integer quantity;

    private LocalDateTime createTime;
    private LocalDateTime cancelTime;
    private LocalDateTime shippedTime;
    private LocalDateTime paymentDeadline;
    private String cancelReason;

    // 其他字段...
}
