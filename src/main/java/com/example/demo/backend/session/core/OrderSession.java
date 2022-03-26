package com.example.demo.backend.session.core;

import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.table.core.RestaurantTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_sessions")
@NoArgsConstructor
public class OrderSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "session_order_fk", referencedColumnName = "id")
    private List<Order> orders;

    @OneToOne(mappedBy = "orderSession")
    private RestaurantTable restaurantTable;

    public void addOrder(Order order) {
        if (!orders.isEmpty()) {
            var lastOrder = orders.get(orders.size() - 1);

            if (lastOrder.getOrderStatus() != OrderStatus.CLOSED) {
                throw new RuntimeException("An order is already in progress");
            }
        } else {
            orders.add(order);
        }
    }

    public OrderSessionStatus getSessionStatus() {
        if(orders.isEmpty()) {
            return OrderSessionStatus.CAN_ORDER;
        }

        var lastOrder = orders.get(orders.size() - 1);

        return switch (lastOrder.getOrderStatus()) {
            case WAITING -> OrderSessionStatus.SENT_TO_RESTAURANT;
            case IN_PROGRESS -> OrderSessionStatus.IN_THE_MAKING;
            case CLOSED -> OrderSessionStatus.CAN_ORDER;
        };
    }
}
