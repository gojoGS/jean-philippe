package com.example.demo.backend.restaurant.service;

public interface RestaurantServiceFactory {
    RestaurantService getRestaurantService(long restaurantId);
}
