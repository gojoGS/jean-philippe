package com.example.demo.backend.restaurant.service.base;

import java.util.Collection;

public interface EntityService<T> {
    void add(T t);

    Collection<T> getAll();

    void update(T t);

    void remove(T t);
}
