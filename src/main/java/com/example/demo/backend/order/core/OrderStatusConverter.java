package com.example.demo.backend.order.core;

import com.example.demo.backend.dish.core.DishType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        }

        return orderStatus.getStatus();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        return Stream.of(OrderStatus.values())
                .filter(c -> c.getStatus().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
