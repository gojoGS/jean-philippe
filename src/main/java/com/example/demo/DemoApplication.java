package com.example.demo;

import com.example.demo.backend.dish.repository.DishRepository;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        return args -> {
            
        };
    }
}
