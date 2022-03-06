package com.example.demo.backend.restaurant.service.beverage;

public interface BeverageServiceFactory {
    BeverageService get(long restaurantId);
}
