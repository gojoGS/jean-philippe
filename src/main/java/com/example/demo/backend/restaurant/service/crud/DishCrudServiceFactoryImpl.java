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
        return new DishCrudServiceImpl(restaurantId);
    }

    private class DishCrudServiceImpl implements CrudListener<Dish> {

        private final RestaurantService restaurantService;

        public DishCrudServiceImpl(long restaurantId) {
            restaurantService = serviceFactory.getRestaurantService(restaurantId);
        }

        @Override
        public Collection<Dish> findAll() {
            return restaurantService.getAll();
        }

        @Override
        public Dish add(Dish dish) {
            restaurantService.addDish(dish);
            return dish;
        }

        @Override
        public Dish update(Dish dish) {
            restaurantService.update(dish);
            return dish;
        }

        @Override
        public void delete(Dish dish) {
            restaurantService.removeDish(dish);
        }
    }
}
