package com.example.demo.backend.restaurant.service.base;

import com.example.demo.backend.beverage.core.Beverage;

import java.util.Collection;

public interface EntityService<T> {
    void add(T t);

    Collection<T> getAll();

    void update(T t);

    void remove(T t);
}
