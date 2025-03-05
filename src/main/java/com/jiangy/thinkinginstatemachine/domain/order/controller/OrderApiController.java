package com.jiangy.thinkinginstatemachine.domain.order.controller;

import com.jiangy.thinkinginstatemachine.domain.order.CreateOrderResponse;
import com.jiangy.thinkinginstatemachine.domain.order.OrderRequest;
import com.jiangy.thinkinginstatemachine.domain.order.entity.Order;
import com.jiangy.thinkinginstatemachine.domain.order.repository.OrderRepository;
import com.jiangy.thinkinginstatemachine.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>创建时间: 2025/3/5 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderApiController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;


    @GetMapping
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    public CreateOrderResponse createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }
}
