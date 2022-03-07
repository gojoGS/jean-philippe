package com.example.demo.backend.restaurant.service.table;

public interface TableServiceFactory {
    TableService get(long restaurantId);
}
