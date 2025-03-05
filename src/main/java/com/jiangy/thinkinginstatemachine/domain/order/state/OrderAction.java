package com.jiangy.thinkinginstatemachine.domain.order.state;

import com.jiangy.thinkinginstatemachine.domain.order.entity.Order;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.service.DepositService;
import com.jiangy.thinkinginstatemachine.domain.order.service.InventoryService;
import com.jiangy.thinkinginstatemachine.domain.order.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class OrderAction {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private NotificationService notificationService;

    public Action<OrderStates, OrderEvents> handleUserOrder() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);

            // 占用库存
            inventoryService.reserveInventory(
                    order.getProductId(),
                    order.getQuantity()
            );

            // 占用押金
            depositService.reserveDeposit(
                    order.getBuyerId(),
                    order.getTotalAmount()
            );

            // 发送通知
            notificationService.sendOrderCreatedNotification(order);
        };
    }

    public Action<OrderStates, OrderEvents> handleTimeoutCancel() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);

            // 释放库存
            inventoryService.releaseInventory(
                    order.getProductId(),
                    order.getQuantity()
            );

            // 释放押金
            depositService.releaseDeposit(
                    order.getBuyerId(),
                    order.getTotalAmount()
            );

            // 更新订单状态
            order.setCancelReason("超时未发货");
            order.setCancelTime(LocalDateTime.now());

            // 发送通知
            notificationService.sendOrderCanceledNotification(order);
        };
    }

    public Action<OrderStates, OrderEvents> handleSellerShipped() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);
            // 更新订单状态
            order.setShippedTime(LocalDateTime.now());
        };
    }
}
