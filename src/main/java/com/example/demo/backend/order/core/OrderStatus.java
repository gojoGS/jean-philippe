package com.example.demo.backend.order.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    WAITING("Waiting"),
    IN_PROGRESS("In progress"),
    CLOSED("Closed");

    private final String status;
}
