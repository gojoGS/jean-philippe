package com.example.demo.security.user.restaurant.service.user;

import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.core.RestaurantDetailsDto;
import com.example.demo.security.user.restaurant.core.RestaurantUser;
import com.example.demo.security.user.restaurant.core.RestaurantUserDto;
import com.example.demo.security.user.restaurant.repository.RestaurantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantUserServiceImpl implements RestaurantUserService {
    @Autowired
    RestaurantUserRepository repository;

    @Override
    public boolean isEmailInUse(String email) {
        return !repository.findByEmail(email).isEmpty();
    }

    @Override
    public void createNewRestaurantUser(RestaurantUserDto userDto, RestaurantDetailsDto detailsDto) {
        RestaurantUser user = new RestaurantUser(
                userDto.getEmail(),
                userDto.getEncryptedPassword(),
                new Restaurant(
                        detailsDto.getName(), detailsDto.getDescription()
                ));

        repository.save(user);
    }

    @Override
    public Optional<RestaurantUser> getUser(String email) {
        return repository.getByEmail(email);
    }
}
