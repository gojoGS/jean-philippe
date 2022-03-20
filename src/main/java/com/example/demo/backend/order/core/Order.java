package com.example.demo.backend.order.core;

import com.example.demo.backend.item.Item;
import com.example.demo.backend.server.core.Server;
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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private OrderStatus orderStatus;
    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    public Order() {
        orderStatus = OrderStatus.OPEN;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if(this.orderStatus != OrderStatus.OPEN) {
            throw new RuntimeException("Can't add items to an order, that's not open");
        }

        items.add(item);
    }

    // TOPIC streamek
    public long getTotalPrice() {
        return items.stream().mapToLong(Item::getPriceInHuf).sum();
    }


}
