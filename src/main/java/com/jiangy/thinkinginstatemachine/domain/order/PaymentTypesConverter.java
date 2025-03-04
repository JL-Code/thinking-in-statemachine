package com.jiangy.thinkinginstatemachine.domain.order;

import com.jiangy.thinkinginstatemachine.domain.order.enums.PaymentTypes;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Converter
public class PaymentTypesConverter implements AttributeConverter<PaymentTypes, String> {

    @Override
    public String convertToDatabaseColumn(PaymentTypes attribute) {
        return attribute == null ? PaymentTypes.POSTPAID.name() : attribute.name();
    }

    @Override
    public PaymentTypes convertToEntityAttribute(String dbData) {
        return StringUtils.hasText(dbData) ? PaymentTypes.valueOf(dbData) : PaymentTypes.POSTPAID;
    }
}