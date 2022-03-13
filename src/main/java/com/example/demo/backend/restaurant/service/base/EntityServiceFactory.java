package com.example.demo.backend.restaurant.service.base;

// TOPIC factory pattern and why i needs it
public interface EntityServiceFactory<T> {
    EntityService<T> get(long restaurantId);
}
