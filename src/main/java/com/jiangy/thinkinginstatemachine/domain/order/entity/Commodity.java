package com.jiangy.thinkinginstatemachine.domain.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品
 * <p>创建时间: 2025/3/5 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Data
@Entity(name = "order_commodity")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;
    private Integer quantity;
    /**
     * 状态（0-下架 1-上架）
     */
    private Integer status;

    @Version
    private Integer version;
}
