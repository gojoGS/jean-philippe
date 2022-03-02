package com.example.demo.backend.restaurant.service.dish;

import com.example.demo.backend.dish.core.Dish;

import java.util.Collection;

public interface DishService {
    void addDish(Dish dish);

    Collection<Dish> getAll();

    void update(Dish dish);

    void removeDish(Dish dish);
}
