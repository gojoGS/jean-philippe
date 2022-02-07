package com.example.demo.backend.restaurant.rest;


import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rest/restaurant")
public class RestaurantController {
    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    RestaurantDto get() {
        return new RestaurantDto(12L, "asdasddas");
    }

    @GetMapping("/")
    List<RestaurantDto> getAllRestaurant() {
        return repository
                .findAll()
                .stream()
                .map(RestaurantDto::new)
                .toList();
    }
}
