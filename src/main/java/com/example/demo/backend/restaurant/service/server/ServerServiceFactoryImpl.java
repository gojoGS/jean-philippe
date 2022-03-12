package com.example.demo.backend.restaurant.service.server;

import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.example.demo.backend.restaurant.service.base.EntityService;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.server.core.Server;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Slf4j
public class ServerServiceFactoryImpl implements EntityServiceFactory<Server> {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public EntityService<Server> get(long restaurantId) {
        return new ServerServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class ServerServiceImpl implements EntityService<Server> {
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
        public void add(Server server) {
            var restaurant = getRestaurant();
            restaurant.addServer(server);
            restaurantRepository.save(restaurant);
        }

        @Override
        public Collection<Server> getAll() {
            return getRestaurant().getServers();
        }

        @Override
        public void update(Server server) {
            var restaurant = getRestaurant();
            restaurant.updateServer(server);
            restaurantRepository.save(restaurant);
        }

        @Override
        public void remove(Server server) {
            var restaurant = getRestaurant();
            restaurant.removeServer(server);
            restaurantRepository.save(restaurant);
        }
    }
}
