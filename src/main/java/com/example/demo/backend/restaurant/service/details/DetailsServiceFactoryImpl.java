package com.example.demo.backend.restaurant.service.details;

import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DetailsServiceFactoryImpl implements DetailsServiceFactory{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public DetailsService get(long restaurantId) {
        return new DetailsServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class DetailsServiceImpl implements DetailsService {
        private long id;

        private Restaurant getRestaurant() {
            var result = restaurantRepository.findById(id);

            if (result.isEmpty()) {
                log.error(String.format("restaurant id not found: %d", id));
                throw new RuntimeException(String.format("restaurant id not found: %d", id));
            }

            return result.get();
        }

        @Override
        public String getName() {
            return getRestaurant().getName();
        }

        @Override
        public void setName(String name) {
            var restaurant = getRestaurant();
            restaurant.setName(name);
            restaurantRepository.save(restaurant);
        }

        @Override
        public String getDescription() {
            return getRestaurant().getDescription();
        }

        @Override
        public void setDescription(String description) {
            var restaurant = getRestaurant();
            restaurant.setDescription(description);
            restaurantRepository.save(restaurant);
        }
    }
}
