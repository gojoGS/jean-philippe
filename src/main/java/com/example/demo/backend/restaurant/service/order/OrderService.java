package com.example.demo.backend.restaurant.service.order;

import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByStatus(OrderStatus status);
}
