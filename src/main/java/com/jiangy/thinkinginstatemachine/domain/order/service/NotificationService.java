package com.jiangy.thinkinginstatemachine.domain.order.service;

import com.jiangy.thinkinginstatemachine.domain.order.entity.Order;
import org.springframework.stereotype.Component;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class NotificationService {
    public void sendOrderCreatedNotification(Order order) {
        System.out.println("Order created：" + order.getOrderNo());
    }

    public void sendOrderCanceledNotification(Order order) {
        System.out.println("Order canceled：" + order.getOrderNo());
    }
}
