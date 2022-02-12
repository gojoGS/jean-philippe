package com.example.demo.backend.restaurant.service.restaurant;

import com.example.demo.backend.dish.core.Dish;

import java.util.Collection;

// TOPIC why createRestaurant isnt here? SOLID separate interfaces
public interface RestaurantService {
    void addDish(Dish dish);

    Collection<Dish> getAll();

    void update(Dish dish);

    void removeDish(Dish dish);

    void renameRestaurant(String newName);

    void editDescription(String newDescription);
}
