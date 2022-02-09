package com.example.demo.backend.restaurant.service;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantServiceFactoryImpl implements RestaurantServiceFactory {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantService getRestaurantService(long restaurantId) {
        return null;
    }

    private class RestaurantServiceImpl implements RestaurantService {
        private long restaurantId;

        @Override
        public void addDish(Dish dish) {
            var result = restaurantRepository.findById(restaurantId);

            if (result.isEmpty()) {
                // TODO should we throw?
                return;
            }

            var restaurant = result.get();
            restaurant.getDishes().add(dish);
            restaurantRepository.save(restaurant);
        }

        @Override
        public void removeDish(Dish dish) {

        }

        @Override
        public void renameRestaurant(String newName) {

        }
    }
}
