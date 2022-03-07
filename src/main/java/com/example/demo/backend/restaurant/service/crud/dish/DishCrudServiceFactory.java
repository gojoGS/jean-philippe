package com.example.demo.backend.restaurant.service.crud.dish;

import com.example.demo.backend.dish.core.Dish;
import org.vaadin.crudui.crud.CrudListener;

public interface DishCrudServiceFactory {
    CrudListener<Dish> getDishCrudService(long restaurantId);
}
