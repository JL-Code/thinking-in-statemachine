package com.jiangy.thinkinginstatemachine.domain.order.service;

import com.jiangy.thinkinginstatemachine.domain.order.CreateOrderResponse;
import com.jiangy.thinkinginstatemachine.domain.order.entity.Order;
import com.jiangy.thinkinginstatemachine.domain.order.OrderRequest;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderEvents;
import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import com.jiangy.thinkinginstatemachine.domain.order.enums.TradeStatus;
import com.jiangy.thinkinginstatemachine.domain.order.repository.OrderRepository;
import com.jiangy.thinkinginstatemachine.domain.order.strategy.PaymentResponse;
import com.jiangy.thinkinginstatemachine.domain.order.strategy.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Slf4j
@Service
@Transactional
public class OrderService {
    private static final String TAG = "订单服务";

    @Autowired
    private Map<String, PaymentStrategy> paymentStrategies;

    @Autowired
    private StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory;

    @Autowired
    private OrderRepository orderRepository;

    public CreateOrderResponse createOrder(OrderRequest request) {
        try {
            Order order = new Order();
            BeanUtils.copyProperties(request, order);
            order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
            order.setBuyerId(1L);
            order.setMerchantId(2L);

            if (Objects.requireNonNull(request.getPaymentType()) == PaymentTypes.PREPAID) {
                // 设置支付截止时间，默认 15 分钟后截止。
                order.setPaymentDeadline(LocalDateTime.now().plusMinutes(15));
                // 设置交易状态为处理中
                order.setTradeStatus(TradeStatus.P);
            }

            // 计算价格
            order.setTotalAmount(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));

            log.info("[{}] 商品：{},单价：{},数量：{},订单金额：{},付费类型：{}",
                    TAG,
                    order.getProductId(),
                    order.getPrice(),
                    order.getQuantity(),
                    order.getTotalAmount(),
                    order.getPaymentType()
            );

            // 选择支付策略
            PaymentStrategy strategy = paymentStrategies.get(
                    request.getPaymentType().name().toLowerCase() + "Strategy"
            );
            // 执行支付验证
            strategy.validate(order);

            // 处理支付并获取支付信息
            Optional<PaymentResponse> opt = strategy.process(order);

            stateMachineFactory.getStateMachine("");

            // 保存交易信息
            orderRepository.save(order);

            return new CreateOrderResponse()
                    .setOrderNo(order.getOrderNo())
                    .setPaymentType(request.getPaymentType())
                    .setPaymentResponse(opt.orElse(null));

        } catch (Exception e) {
            log.error("[{}] 下单失败：{}", TAG, e.getMessage());
            throw e;
        }
    }

    public void processPaymentResult(String paymentChannel, Map<String, String> callbackParams) {
        log.info("[{}] 处理支付结果，paymentChannel:{} callbackParams:{}", TAG, paymentChannel, callbackParams);

        // UN_PAID->TO_DELIVER
        String tradeNo = callbackParams.get("trade_no");
        String orderNo = callbackParams.get("out_trade_no");
        // 订单金额。本次交易支付的订单金额，单位为人民币（元）。支持小数点后两位。
        String totalAmount = callbackParams.get("total_amount");
        // 实收金额。商家在交易中实际收到的款项，单位为人民币（元）。支持小数点后两位。
        String receiptAmount = callbackParams.get("receipt_amount");
        String tradeStatus = callbackParams.get("trade_status");
        var order = orderRepository.findByOrderNo(orderNo).orElseThrow();

        if (order.getTradeStatus() == TradeStatus.S) {
            throw new RuntimeException("订单已经支付，发生了重复付款，记录下后续客服手动退款");
        }

        if (Objects.equals(tradeStatus, "TRADE_SUCCESS")) {
            // 更新支付信息
            order.setPaymentTransactionId(tradeNo);
            order.setStatus(OrderStates.TO_DELIVER);
            order.setTradeStatus(TradeStatus.S);
        } else {
            order.setTradeStatus(TradeStatus.F);
        }

        orderRepository.save(order);
    }
}