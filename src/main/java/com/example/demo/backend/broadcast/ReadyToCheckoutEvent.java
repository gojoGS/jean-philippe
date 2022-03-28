package com.example.demo.backend.broadcast;

import com.example.demo.backend.payment.PaymentMethod;
import lombok.Getter;

@Getter
public class ReadyToCheckoutEvent extends Event {
    long tableId;
    long tableNumber;
    PaymentMethod paymentMethod;

    public ReadyToCheckoutEvent(EventType type, long sessionId, long tableId, long tableNumber, PaymentMethod paymentMethod) {
        super(type, sessionId);
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.paymentMethod = paymentMethod;
    }
}
