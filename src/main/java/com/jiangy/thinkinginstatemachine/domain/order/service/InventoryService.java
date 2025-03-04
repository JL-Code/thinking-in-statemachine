package com.jiangy.thinkinginstatemachine.domain.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Slf4j
@Service
public class InventoryService {

    public boolean checkInventory(Integer productId, Integer quantity) {
        log.info("[库存服务] 检查 {} 库存 {}", productId, quantity);
        return true;
    }

    public void reserveInventory(Integer productId, Integer quantity) {
        log.info("[库存服务] 占用 {} 库存 {}", productId, quantity);
    }

    public void releaseInventory(Integer productId, Integer quantity) {
        log.info("[库存服务] 释放 {} 库存 {}", productId, quantity);
    }
}
