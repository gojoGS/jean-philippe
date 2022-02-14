package com.example.demo.security.user.restaurant.service;

import com.example.demo.backend.restaurant.core.RestaurantDetailsDto;
import com.example.demo.security.user.restaurant.core.RestaurantUserDto;

public interface RestaurantUserService {
    boolean isEmailInUse(String email);

    void createNewRestaurantUser(RestaurantUserDto userDto, RestaurantDetailsDto detailsDto);

    // TODO delete user
}
