package com.jiangy.thinkinginstatemachine.domain.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Slf4j
@Component
public class DepositService {
    public void reserveDeposit(Long buyerId, BigDecimal totalAmount) {
        log.info("[押金服务] 占用 {} 库存 {}", buyerId, totalAmount);
    }

    public void releaseDeposit(Long buyerId, BigDecimal totalAmount) {
        log.info("[押金服务] 释放 {} 库存 {}", buyerId, totalAmount);
    }
}
