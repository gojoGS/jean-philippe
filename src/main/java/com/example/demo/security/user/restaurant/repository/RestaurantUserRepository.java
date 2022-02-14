package com.example.demo.security.user.restaurant.repository;

import com.example.demo.security.user.restaurant.core.RestaurantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, Long> {
    List<RestaurantUser> findByEmail(String email);

    // TOPIC these method name queries
    // TODO delete findbyemail and use this
    Optional<RestaurantUser> getByEmail(String email);
}
