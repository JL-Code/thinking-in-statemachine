package com.jiangy.thinkinginstatemachine.domain.order.state;

import com.jiangy.thinkinginstatemachine.domain.order.Order;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import com.jiangy.thinkinginstatemachine.domain.order.service.CreditService;
import com.jiangy.thinkinginstatemachine.domain.order.service.PaymentService;
import com.jiangy.thinkinginstatemachine.domain.order.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class PaymentGuard {

    @Autowired
    private CreditService creditService;
    @Autowired
    private PaymentService paymentService;

    public Guard<OrderStates, OrderEvents> prepaidGuard() {
        return context -> {
            var result = OrderUtils.getOrder(context);
            return result.filter(order -> order.getPaymentType() == PaymentTypes.PREPAID
                    && paymentService.checkFunds(order.getBuyerId(), order.getTotalAmount())).isPresent();
        };
    }

    public Guard<OrderStates, OrderEvents> postpaidGuard() {
        return context -> {
            var result = OrderUtils.getOrder(context);
            return result.filter(order -> order.getPaymentType() == PaymentTypes.POSTPAID
                    && creditService.checkCreditScore(order.getBuyerId()) > 650).isPresent();
        };
    }
}
