package com.jiangy.thinkinginstatemachine.domain.order;

import com.jiangy.thinkinginstatemachine.domain.order.enums.TradeStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Converter
public class TradeStatusConverter implements AttributeConverter<TradeStatus, String> {

    @Override
    public String convertToDatabaseColumn(TradeStatus attribute) {
        return attribute == null ? TradeStatus.N.name() : attribute.name();
    }

    @Override
    public TradeStatus convertToEntityAttribute(String dbData) {
        return StringUtils.hasText(dbData) ? TradeStatus.valueOf(dbData) : TradeStatus.N;
    }
}