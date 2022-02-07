package com.example.demo.backend.restaurant.rest;

import com.example.demo.backend.restaurant.core.Restaurant;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantDto {
    private Long id;
    private String name;

    public RestaurantDto(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName());
    }
}
