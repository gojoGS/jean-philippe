package com.example.demo.backend.restaurant.service.order;

public interface OrderServiceFactory {
    OrderService get(long restaurantId);
}
