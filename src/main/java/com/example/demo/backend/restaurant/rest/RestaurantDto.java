package com.example.demo.backend.restaurant.rest;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.core.Restaurant;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantDto {
    private Long id;
    private String name;
    private List<Dish> dishes;
    private String description;

    public RestaurantDto(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDishes(), restaurant.getDescription());
    }
}
