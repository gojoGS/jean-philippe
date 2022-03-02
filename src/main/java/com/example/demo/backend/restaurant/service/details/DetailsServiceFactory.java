package com.example.demo.backend.restaurant.service.details;

public interface DetailsServiceFactory {
    DetailsService get(long restaurantId);
}
