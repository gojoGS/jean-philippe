package com.example.demo.backend.restaurant.service.restaurant;

public interface RestaurantServiceFactory {
    RestaurantService getRestaurantService(long restaurantId);
}
