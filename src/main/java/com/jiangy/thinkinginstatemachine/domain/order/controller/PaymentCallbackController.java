package com.jiangy.thinkinginstatemachine.domain.order.controller;

import com.jiangy.thinkinginstatemachine.domain.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>创建时间: 2025/3/5 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pay/callback")
public class PaymentCallbackController {
    private final OrderService orderService;

    /**
     * 支付回调处理
     *
     * @param paymentChannel 支付渠道
     * @return 处理结果
     */
    @PostMapping("/{paymentChannel}")
    public String handlePayCallback(@PathVariable String paymentChannel, HttpServletRequest request) {
        try {
            // 处理支付结果
            orderService.processPaymentResult(paymentChannel, convertToSingleValueMap(request));
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    public static Map<String, String> convertToSingleValueMap(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> singleValueMap = new HashMap<>(16);

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue().length > 0) {
                singleValueMap.put(entry.getKey(), entry.getValue()[0]);
            }
        }

        return singleValueMap;
    }
}
