package com.example.demo.backend.restaurant.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantDetailsDto {
    private String name;
    private String description;
}
