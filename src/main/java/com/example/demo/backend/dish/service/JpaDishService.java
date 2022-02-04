package com.example.demo.backend.dish.service;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.dish.core.DishType;
import com.example.demo.backend.dish.repository.DishRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaDishService implements DishService {
    private final DishRepository dishRepository;

    @Override
    public Optional<Dish> get(long id) {
        return dishRepository.findById(id);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public void add(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public void remove(long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> getAllInType(DishType type) {
        return dishRepository.findAll()
                .stream()
                .filter(dish -> dish.getType().equals(type))
                .toList();
    }
}
