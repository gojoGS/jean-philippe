package com.example.demo.backend.restaurant.service;

import com.example.demo.backend.dish.core.Dish;

// TOPIC why createRestaurant isnt here? SOLID separate interfaces
public interface RestaurantService {
    void addDish(Dish dish);

    void removeDish(Dish dish);

    void renameRestaurant(String newName);
}
