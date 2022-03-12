package com.example.demo.backend.restaurant.service.table;

import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.example.demo.backend.restaurant.service.base.EntityService;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.table.core.Table;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Slf4j
public class TableServiceFactoryImpl implements EntityServiceFactory<Table> {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public EntityService<Table> get(long restaurantId) {
        return new TableServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class TableServiceImpl implements EntityService<Table> {
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
        public void add(Table table) {
            var restaurant = getRestaurant();
            restaurant.addTable(table);
            restaurantRepository.save(restaurant);
        }

        @Override
        public Collection<Table> getAll() {
            return getRestaurant().getTables();
        }

        @Override
        public void update(Table table) {
            var restaurant = getRestaurant();
            restaurant.updateTable(table);
            restaurantRepository.save(restaurant);
        }

        @Override
        public void remove(Table table) {
            var restaurant = getRestaurant();
            restaurant.removeTable(table);
            restaurantRepository.save(restaurant);
        }
    }
}
