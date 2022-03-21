package com.example.demo.backend.item.service;

import com.example.demo.backend.item.core.Item;
import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Optional<Restaurant> getRestaurantOfItem(Item item) {
        var restaurants = restaurantRepository.findAll();

       return restaurants
                .stream()
                .filter(restaurant -> restaurant.getDishes().contains(item) || restaurant.getBeverages().contains(item))
                .findFirst();
    }
}
