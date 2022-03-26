package com.example.demo.backend.restaurant.service.order;

import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.order.repository.OrderRepository;
import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.example.demo.backend.restaurant.service.base.EntityService;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.session.core.OrderSession;
import com.example.demo.backend.table.core.RestaurantTable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class OrderServiceFactoryImpl implements OrderServiceFactory {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public OrderService get(long restaurantId) {
        return new OrderServiceImpl(restaurantId);
    }

    @AllArgsConstructor
    private class OrderServiceImpl implements OrderService {
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
        public List<Order> getOrdersByStatus(OrderStatus status) {
            return orderRepository.findAll()
                    .stream()
                    .filter(order -> order.getOrderSession().getRestaurantTable().getRestaurant().getId() == id)
                    .filter(order -> order.getOrderStatus() == status)
                    .toList();
        }
    }
}
