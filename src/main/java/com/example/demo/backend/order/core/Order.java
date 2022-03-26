package com.example.demo.backend.order.core;

import com.example.demo.backend.item.core.Item;
import com.example.demo.backend.server.core.Server;
import com.example.demo.backend.session.core.OrderSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "session_order_fk")
    private OrderSession orderSession;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    // TOPIC streamek
    public long getTotalPrice() {
        return items.stream().mapToLong(Item::getPriceInHuf).sum();
    }


}
