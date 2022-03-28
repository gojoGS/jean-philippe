package com.example.demo.backend.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMethod {
    CASH("Cash"),
    CARD("Card"),
    CRYPTO("Crypto");

    final String name;
}
