package com.example.demo.backend.restaurant.service.base;

import lombok.AllArgsConstructor;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

// TOPIC adapter pattern
// TOPIC generics
@AllArgsConstructor
public class EntityServiceAdapter<T> implements CrudListener<T> {
    private final EntityService<T> service;

    @Override
    public Collection<T> findAll() {
        return service.getAll();
    }

    @Override
    public T add(T t) {
        service.add(t);
        return t;
    }

    @Override
    public T update(T t) {
        service.update(t);
        return t;
    }

    @Override
    public void delete(T t) {
        service.remove(t);
    }
}
