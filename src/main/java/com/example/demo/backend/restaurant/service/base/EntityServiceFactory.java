package com.example.demo.backend.restaurant.service.base;

public interface EntityServiceFactory<T> {
    EntityService<T> get(long restaurantId);
}
