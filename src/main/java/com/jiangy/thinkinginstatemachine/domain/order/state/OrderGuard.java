package com.jiangy.thinkinginstatemachine.domain.order.state;

import com.jiangy.thinkinginstatemachine.domain.order.Order;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.service.InventoryService;
import com.jiangy.thinkinginstatemachine.domain.order.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class OrderGuard {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private InventoryService inventoryService;

    public Guard<OrderStates, OrderEvents> userOrderGuard() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);

            // 商家相关检查
            return merchantService.isEnabled(order.getMerchantId())
                    && merchantService.isRealNameVerified(order.getMerchantId())
                    && !merchantService.isBlacklisted(order.getMerchantId(), order.getBuyerId())
                    && inventoryService.checkInventory(order.getProductId(), order.getQuantity());
        };
    }

    public Guard<OrderStates, OrderEvents> timeoutCancelGuard() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);
            return order.getCreateTime().plusHours(48).isBefore(LocalDateTime.now());
        };
    }
}