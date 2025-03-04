package com.jiangy.thinkinginstatemachine.domain.order;

import com.jiangy.thinkinginstatemachine.domain.order.enums.OrderStates;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Converter
public class OrderStatesConverter implements AttributeConverter<OrderStates, String> {

    @Override
    public String convertToDatabaseColumn(OrderStates attribute) {
        return attribute == null ? OrderStates.INITIAL.name() : attribute.name();
    }

    @Override
    public OrderStates convertToEntityAttribute(String dbData) {
        return StringUtils.hasText(dbData) ? OrderStates.valueOf(dbData) : OrderStates.INITIAL;
    }
}