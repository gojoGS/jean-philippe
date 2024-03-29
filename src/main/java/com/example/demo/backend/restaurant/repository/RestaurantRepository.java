package com.example.demo.backend.restaurant.repository;

import com.example.demo.backend.restaurant.core.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
