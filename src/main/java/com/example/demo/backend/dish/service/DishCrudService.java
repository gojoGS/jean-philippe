package com.example.demo.backend.dish.service;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.dish.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DishCrudService implements CrudListener<Dish> {
    private final DishRepository dishRepository;

    @Override
    public Collection<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish add(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void delete(Dish dish) {
        dishRepository.delete(dish);
    }
}
