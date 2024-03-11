package com.dit.spring.kafka.udemy.intro.dispatch.service;

import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderCreated;
import com.dit.spring.kafka.udemy.intro.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class DispatchServiceTest {

    private DispatchService dispatchService;

    @BeforeEach
    void setUp() {
        dispatchService = new DispatchService();
    }

    @Test
    void process() {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        dispatchService.process(testEvent);
    }
}