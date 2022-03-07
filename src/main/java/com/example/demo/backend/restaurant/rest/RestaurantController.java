package com.example.demo.backend.restaurant.rest;


import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.core.RestaurantDto;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/rest/restaurant")
public class RestaurantController {
    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    List<RestaurantDto> getAllRestaurant() {
        return repository
                .findAll()
                .stream()
                .map(RestaurantDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    Optional<RestaurantDto> getRestaurantById(@PathVariable String id) {
        long restaurantId = Long.parseLong(id);

        return repository
                .findById(restaurantId)
                .map(RestaurantDto::new);
    }

    @GetMapping("/{id}/beverages")
    List<Beverage> getBeveragesOfRestaurantById(@PathVariable String id) {


        // TOPIC valueOf vs parseLong
        // autoboxing stb
        long restaurantId = Long.parseLong(id);

        var result = repository.findById(restaurantId);

        if(result.isEmpty()) {
            return List.of();
        }

        return result
                .get()
                .getBeverages()
                .stream()
                .toList();
    }

    @GetMapping("/{id}/dishes")
    List<Dish> getDishesOfRestaurantById(@PathVariable String id) {
        long restaurantId = Long.parseLong(id);

        var result = repository.findById(restaurantId);

        if(result.isEmpty()) {
            return List.of();
        }

        return result
                .get()
                .getDishes()
                .stream()
                .toList();
    }
}
