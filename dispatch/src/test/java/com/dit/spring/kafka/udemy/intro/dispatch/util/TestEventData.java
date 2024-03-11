package com.dit.spring.kafka.udemy.intro.dispatch.util;

import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderCreated;

import java.util.UUID;

public class TestEventData {

    public static OrderCreated buildOrderCreatedEvent(UUID orderId, String item) {
        return OrderCreated.builder()
                .orderId(orderId)
                .item(item)
                .build();
    }
}
