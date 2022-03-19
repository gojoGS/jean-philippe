package com.example.demo.backend.order.core;

import com.example.demo.backend.order.Payable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private OrderStatus orderStatus;
    private List<Payable> items;

    public Order() {
        orderStatus = OrderStatus.OPEN;
        items = new ArrayList<>();
    }

    public void addItem(Payable item) {
        items.add(item);
    }

    // TOPIC streamek
    public long getTotalPrice() {
        return items.stream().mapToLong(Payable::getPrice).sum();
    }
}
