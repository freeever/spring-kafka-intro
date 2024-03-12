package com.dit.spring.kafka.udemy.tracking.util;


import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchPreparing;

import java.util.UUID;

public class TestEventData {

    public static DispatchPreparing buildDispatchPreparingEvent(UUID orderId) {
        return DispatchPreparing.builder()
                .orderId(orderId)
                .build();
    }

}
