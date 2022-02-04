package com.example.demo.backend.dish.service;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.dish.core.DishType;

import java.util.List;
import java.util.Optional;

public interface DishService {
    Optional<Dish> get(long id);
    List<Dish> getAll();
    void add(Dish dish);
    void remove(long id);
    List<Dish> getAllInType(DishType type);
}
