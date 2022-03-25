package com.example.demo.security.user.restaurant.service.user;

import com.example.demo.backend.restaurant.core.RestaurantDetailsDto;
import com.example.demo.security.user.restaurant.core.RestaurantUser;
import com.example.demo.security.user.restaurant.core.RestaurantUserDto;

import java.util.Optional;

public interface RestaurantUserService {
    boolean isEmailInUse(String email);

    void createNewRestaurantUser(RestaurantUserDto userDto, RestaurantDetailsDto detailsDto);

    Optional<RestaurantUser> getUser(String email);

    // TODO delete user
}
