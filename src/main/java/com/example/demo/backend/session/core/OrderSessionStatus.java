package com.example.demo.backend.session.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSessionStatus {
    CAN_ORDER("Can order"),
    SENT_TO_RESTAURANT("Sent to restaurant"),
    IN_THE_MAKING("In the making");

    private final String status;
}
