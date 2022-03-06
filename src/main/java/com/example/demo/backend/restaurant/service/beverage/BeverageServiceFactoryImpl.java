package com.example.demo.backend.restaurant.service.beverage;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Slf4j
public class BeverageServiceFactoryImpl implements BeverageServiceFactory{
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public BeverageService get(long restaurantId) {
        return new BeverageServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class BeverageServiceImpl implements BeverageService {
        private final long restaurantId;

        private Restaurant getRestaurant() {
            var result = restaurantRepository.findById(restaurantId);

            if (result.isEmpty()) {
                log.error(String.format("restaurant id not found: %d", restaurantId));
                throw new RuntimeException(String.format("restaurant id not found: %d", restaurantId));
            }

            return result.get();
        }

        @Override
        public void addBeverage(Beverage beverage) {
            var restaurant = getRestaurant();
            restaurant.addBeverage(beverage);
            restaurantRepository.save(restaurant);
        }

        @Override
        public Collection<Beverage> getAll() {
            return getRestaurant().getBeverages();
        }

        @Override
        public void update(Beverage beverage) {
            var restaurant = getRestaurant();
            restaurant.updateBeverage(beverage);
            restaurantRepository.save(restaurant);
        }

        @Override
        public void removeBeverage(Beverage beverage) {
            var restaurant = getRestaurant();
            restaurant.removeBeverage(beverage);
            restaurantRepository.save(restaurant);
        }
    }
}
