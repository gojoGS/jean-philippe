package com.example.demo.backend.item.service;

import com.example.demo.backend.item.core.Item;
import com.example.demo.backend.restaurant.core.Restaurant;

import java.util.Optional;

public interface ItemService {
    Optional<Restaurant> getRestaurantOfItem(Item item);
}
