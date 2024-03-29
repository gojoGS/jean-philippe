package com.example.demo;

import com.example.demo.backend.restaurant.core.RestaurantDetailsDto;
import com.example.demo.security.user.restaurant.core.RestaurantUserDto;
import com.example.demo.security.user.restaurant.service.user.RestaurantUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@Slf4j
public class DemoApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(RestaurantUserService userService, PasswordEncoder encoder) {
        return args -> {
            userService.createNewRestaurantUser(
                    new RestaurantUserDto(encoder.encode("asd"), "asd"),
                    new RestaurantDetailsDto("ImiTálja", "Minőségi alapanyagokból, igényes felszolgálás")
            );
            userService.createNewRestaurantUser(
                    new RestaurantUserDto(encoder.encode("asd2"), "asd2"),
                    new RestaurantDetailsDto("Judit Bisztró", "Igényes ételek, minőségi felszolgálás")
            );
            userService.createNewRestaurantUser(
                    new RestaurantUserDto(encoder.encode("asd3"), "asd3"),
                    new RestaurantDetailsDto("Lili konyhája", "Otthonos ízek, otthonos hangulat.")
            );
            userService.createNewRestaurantUser(
                    new RestaurantUserDto(encoder.encode("asd4"), "asd4"),
                    new RestaurantDetailsDto("Zöldfa Étterem", "Ételben, itablan az élen vagyunk.")
            );

            log.info("Started application");
        };
    }
}
