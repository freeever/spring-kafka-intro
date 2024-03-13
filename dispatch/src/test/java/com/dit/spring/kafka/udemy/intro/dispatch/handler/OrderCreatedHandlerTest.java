package com.dit.spring.kafka.udemy.intro.dispatch.handler;

import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderCreated;
import com.dit.spring.kafka.udemy.intro.dispatch.service.DispatchService;
import com.dit.spring.kafka.udemy.intro.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler orderCreatedHandler;
    private DispatchService dispatchServiceMock;

    @BeforeEach
    void setUp() {
        dispatchServiceMock = mock(DispatchService.class);
        orderCreatedHandler = new OrderCreatedHandler(dispatchServiceMock);
    }

    @Test
    void listen_Success() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        orderCreatedHandler.listen(testEvent);
        verify(dispatchServiceMock, times(1)).process(testEvent);
    }

    @Test
    void listen_ServiceThrowsException() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Service failure")).when(dispatchServiceMock).process(testEvent);

        orderCreatedHandler.listen(testEvent);

        verify(dispatchServiceMock, times(1)).process(testEvent);
    }
}