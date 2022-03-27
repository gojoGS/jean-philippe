package com.example.demo.backend.broadcast;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Event {
    EventType type;
    long sessionId;
}
