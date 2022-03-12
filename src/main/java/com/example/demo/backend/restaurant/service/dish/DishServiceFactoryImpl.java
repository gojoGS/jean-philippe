package com.example.demo.backend.restaurant.service.dish;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.example.demo.backend.restaurant.service.base.EntityService;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
@Slf4j
public class DishServiceFactoryImpl implements EntityServiceFactory<Dish> {
    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public EntityService<Dish> get(long restaurantId) {
        return new DishServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class DishServiceImpl implements EntityService<Dish> {
        private final long id;

        private Restaurant getRestaurant() {
            var result = restaurantRepository.findById(id);

            if (result.isEmpty()) {
                log.error(String.format("restaurant id not found: %d", id));
                throw new RuntimeException(String.format("restaurant id not found: %d", id));
            }

            return result.get();
        }


        @Override
        public void add(Dish dish) {
            var restaurant = getRestaurant();
            restaurant.getDishes().add(dish);
            restaurantRepository.save(restaurant);
        }

        @Override
        public Collection<Dish> getAll() {
            return getRestaurant().getDishes();
        }

        @Override
        public void update(Dish dish) {
            var restaurant = getRestaurant();

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
        public void remove(Dish dish) {
            var restaurant = getRestaurant();
            restaurant.getDishes().removeIf(dish1 -> Objects.equals(dish.getId(), dish1.getId()));
            restaurantRepository.save(restaurant);
        }
    }
}
