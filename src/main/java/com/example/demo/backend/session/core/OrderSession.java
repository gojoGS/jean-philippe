package com.example.demo.backend.session.core;

import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class OrderSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "session_order_fk", referencedColumnName = "id")
    private List<Order> orders;

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

    public OrderStatus getSessionStatus() {
        if(orders.isEmpty()) {
            return OrderStatus.OPEN;
        } else {
            var lastOrder = orders.get(orders.size() - 1);
            return lastOrder.getOrderStatus();
        }
    }
}
