package com.example.demo.backend.restaurant.service.crud;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.service.restaurant.RestaurantService;
import com.example.demo.backend.restaurant.service.restaurant.RestaurantServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Component
public class DishCrudServiceFactoryImpl implements DishCrudServiceFactory {
    @Autowired
    RestaurantServiceFactory serviceFactory;


    @Override
    public CrudListener<Dish> getDishCrudService(long restaurantId) {
        return null;
    }

    private class DishCrudServiceImpl implements CrudListener<Dish> {

        private RestaurantService restaurantService;

        @Override
        public Collection<Dish> findAll() {
            return null;
        }

        @Override
        public Dish add(Dish dish) {
            return null;
        }

        @Override
        public Dish update(Dish dish) {
            return null;
        }

        @Override
        public void delete(Dish dish) {

        }
    }
}
