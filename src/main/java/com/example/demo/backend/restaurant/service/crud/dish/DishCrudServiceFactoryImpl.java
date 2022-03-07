package com.example.demo.backend.restaurant.service.crud.dish;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.service.dish.DishService;
import com.example.demo.backend.restaurant.service.dish.DishServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Component
public class DishCrudServiceFactoryImpl implements DishCrudServiceFactory {
    private final DishServiceFactory serviceFactory;

    @Autowired
    public DishCrudServiceFactoryImpl(DishServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public CrudListener<Dish> getDishCrudService(long restaurantId) {
        return new DishCrudServiceImpl(restaurantId);
    }

    private class DishCrudServiceImpl implements CrudListener<Dish> {

        private final DishService dishService;

        public DishCrudServiceImpl(long restaurantId) {
            dishService = serviceFactory.get(restaurantId);
        }

        @Override
        public Collection<Dish> findAll() {
            return dishService.getAll();
        }

        @Override
        public Dish add(Dish dish) {
            dishService.addDish(dish);
            return dish;
        }

        @Override
        public Dish update(Dish dish) {
            dishService.update(dish);
            return dish;
        }

        @Override
        public void delete(Dish dish) {
            dishService.removeDish(dish);
        }
    }
}
