package com.example.demo.backend.restaurant.service.restaurant;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Component
public class RestaurantServiceFactoryImpl implements RestaurantServiceFactory {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantService getRestaurantService(long restaurantId) {
        return new RestaurantServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class RestaurantServiceImpl implements RestaurantService {
        private final long restaurantId;

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
        public Collection<Dish> getAll() {
            var result = restaurantRepository.findById(restaurantId);

            if (result.isEmpty()) {
                // TODO should we throw?
                return new ArrayList<>();
            }

            var restaurant = result.get();
            return restaurant.getDishes();
        }

        @Override
        public void update(Dish dish) {
            var result = restaurantRepository.findById(restaurantId);

            if (result.isEmpty()) {
                // TODO should we throw?
                return;
            }

            var restaurant = result.get();
            var resultDish = restaurant.getDishes()
                    .stream()
                    .filter(dish1 -> Objects.equals(dish1.getId(), dish.getId()))
                    .findFirst();

            if (resultDish.isEmpty()) {
                // TODO should we throw?
                return;
            }

            resultDish.get().update(dish);
            restaurantRepository.save(restaurant);
        }

        @Override
        public void removeDish(Dish dish) {
            var result = restaurantRepository.findById(restaurantId);

            if (result.isEmpty()) {
                // TODO should we throw?
                return;
            }

            var restaurant = result.get();
            restaurant.getDishes().removeIf(dish1 -> Objects.equals(dish.getId(), dish1.getId()));
            restaurantRepository.save(restaurant);
        }

        @Override
        public void renameRestaurant(@NotNull String newName) {
            var result = restaurantRepository.findById(restaurantId);

            if (result.isEmpty()) {
                // TODO should we throw?
                return;
            }

            var restaurant = result.get();
            restaurant.setName(newName);
            restaurantRepository.save(restaurant);
        }

        @Override
        public void editDescription(String newDescription) {
            var result = restaurantRepository.findById(restaurantId);

            if (result.isEmpty()) {
                // TODO should we throw?
                return;
            }

            var restaurant = result.get();
            restaurant.setDescription(newDescription);
            restaurantRepository.save(restaurant);
        }
    }
}
