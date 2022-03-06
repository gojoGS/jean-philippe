package com.example.demo.backend.restaurant.core;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.dish.core.Dish;
import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantDto {
    private Long id;
    private String name;
    private Set<Dish> dishes;
    private Set<Beverage> beverages;
    private String description;

    public RestaurantDto(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDishes(), restaurant.getBeverages(), restaurant.getDescription());
    }
}
