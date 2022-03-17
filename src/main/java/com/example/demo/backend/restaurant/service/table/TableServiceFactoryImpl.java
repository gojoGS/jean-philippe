package com.example.demo.backend.restaurant.service.table;

import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.example.demo.backend.restaurant.service.base.EntityService;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.table.core.RestaurantTable;
import com.example.demo.security.service.id.IdGenerationService;
import com.example.demo.security.service.password.generation.PasswordGenerationService;
import com.example.demo.security.user.enduser.core.EndUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;

@Component
@Slf4j
public class TableServiceFactoryImpl implements EntityServiceFactory<RestaurantTable> {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdGenerationService idGenerationService;

    @Autowired
    private PasswordGenerationService passwordGenerationService;

    @Override
    public EntityService<RestaurantTable> get(long restaurantId) {
        return new TableServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class TableServiceImpl implements EntityService<RestaurantTable> {
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
        public void add(RestaurantTable restaurantTable) {
            var restaurant = getRestaurant();

            restaurantTable.setUser(
                    new EndUser(idGenerationService.get(), passwordEncoder.encode(passwordGenerationService.get()))
            );

            restaurant.addTable(restaurantTable);
            restaurantRepository.save(restaurant);
        }

        @Override
        public Collection<RestaurantTable> getAll() {
            return getRestaurant().getRestaurantTables();
        }

        @Override
        public void update(RestaurantTable restaurantTable) {
            var restaurant = getRestaurant();
            restaurant.updateTable(restaurantTable);
            restaurantRepository.save(restaurant);
        }

        @Override
        public void remove(RestaurantTable restaurantTable) {
            var restaurant = getRestaurant();
            restaurant.removeTable(restaurantTable);
            restaurantRepository.save(restaurant);
        }
    }
}
