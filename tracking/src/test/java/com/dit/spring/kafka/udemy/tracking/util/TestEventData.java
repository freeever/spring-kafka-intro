package com.dit.spring.kafka.udemy.tracking.util;


import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchCompleted;
import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchPreparing;

import java.util.UUID;

public class TestEventData {

    public static DispatchPreparing buildDispatchPreparingEvent(UUID orderId) {
        return DispatchPreparing.builder()
                .orderId(orderId)
                .build();
    }

    public static DispatchCompleted buildDispatchCompletedEvent(UUID orderId, String dispatchedDate) {
        return DispatchCompleted.builder()
                .orderId(orderId)
                .dispatchedDate(dispatchedDate)
                .build();
    }

}
