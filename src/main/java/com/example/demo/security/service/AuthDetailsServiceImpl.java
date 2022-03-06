package com.example.demo.security.service;

import com.example.demo.security.user.restaurant.service.RestaurantUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthDetailsServiceImpl implements AuthDetailsService {
    @Autowired
    RestaurantUserService userService;

    @Override
    public long getRestaurantId() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = "";

        // TOPIC pattern matching
        if (principal instanceof UserDetails userDetails) {
            email = userDetails.getUsername();
        }

        var user =
                userService.getUser(email);

        if (user.isEmpty()) {
            log.error("fuck up m8");
            throw new RuntimeException(String.format("User with email %s was not found", email));
        } else {
            return user.get().getRestaurant().getId();
        }
    }

    @Override
    public long getUserId() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = "";

        // TOPIC pattern matching
        if (principal instanceof UserDetails userDetails) {
            email = userDetails.getUsername();
        }

        var user =
                userService.getUser(email);

        if (user.isEmpty()) {
            log.error("fuck up m8");
            throw new RuntimeException(String.format("User with email %s was not found", email));
        } else {
            return user.get().getId();
        }
    }
}
