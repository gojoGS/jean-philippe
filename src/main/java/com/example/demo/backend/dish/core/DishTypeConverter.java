package com.example.demo.backend.dish.core;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DishTypeConverter implements AttributeConverter<DishType, String> {
    @Override
    public String convertToDatabaseColumn(DishType dishType) {
        if (dishType == null) {
            return null;
        }

        return dishType.getName();
    }

    @Override
    public DishType convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        return DishType.valueOf(s);
    }
}
