package com.jiangy.thinkinginstatemachine.domain.order.entity;

import com.jiangy.thinkinginstatemachine.domain.order.OrderStatesConverter;
import com.jiangy.thinkinginstatemachine.domain.order.PaymentTypesConverter;
import com.jiangy.thinkinginstatemachine.domain.order.TradeStatusConverter;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import com.jiangy.thinkinginstatemachine.domain.order.enums.TradeStatus;
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
@Entity(name = "order_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String orderNo;

    @Convert(converter = OrderStatesConverter.class)
    private OrderStates status;

    @Convert(converter = TradeStatusConverter.class)
    private TradeStatus tradeStatus;

    @Convert(converter = PaymentTypesConverter.class)
    private PaymentTypes paymentType;

    private Long merchantId;
    private Long buyerId;
    private Integer productId;
    private String paymentTransactionId;
    private BigDecimal totalAmount;
    private BigDecimal price;
    private Integer quantity;

    private LocalDateTime createTime;
    private LocalDateTime cancelTime;
    private LocalDateTime shippedTime;
    private LocalDateTime paymentDeadline;
    private String cancelReason;

    @Version
    private Integer version;

    // 其他字段...
}
