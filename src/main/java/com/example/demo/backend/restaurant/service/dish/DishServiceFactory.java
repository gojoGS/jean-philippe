package com.example.demo.backend.restaurant.service.dish;

public interface DishServiceFactory {
    DishService get(long restaurantId);
}
