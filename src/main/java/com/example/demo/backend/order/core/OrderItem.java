package com.example.demo.backend.order.core;

import com.example.demo.backend.order.Payable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItem {
    private Payable item;
    private long amount;

    long getValue () {
        return item.getPrice() * amount;
    }
}
