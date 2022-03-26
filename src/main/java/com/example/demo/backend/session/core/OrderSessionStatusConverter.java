package com.example.demo.backend.session.core;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderSessionStatusConverter implements AttributeConverter<OrderSessionStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderSessionStatus orderSessionStatus) {
        if(orderSessionStatus == null) {
            return null;
        }

        return orderSessionStatus.getStatus();
    }

    @Override
    public OrderSessionStatus convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        }

        return Stream.of(OrderSessionStatus.values())
                .filter(o -> o.getStatus().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
