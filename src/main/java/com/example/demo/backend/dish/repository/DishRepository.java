package com.example.demo.backend.dish.repository;

import com.example.demo.backend.dish.core.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> { }