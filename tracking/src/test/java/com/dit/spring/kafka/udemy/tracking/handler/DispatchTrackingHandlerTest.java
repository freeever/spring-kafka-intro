package com.dit.spring.kafka.udemy.tracking.handler;

import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchCompleted;
import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchPreparing;
import com.dit.spring.kafka.udemy.intro.tracking.handler.DispatchTrackingHandler;
import com.dit.spring.kafka.udemy.intro.tracking.service.TrackingService;
import com.dit.spring.kafka.udemy.tracking.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DispatchTrackingHandlerTest {

    private TrackingService trackingServiceMock;

    private DispatchTrackingHandler handler;

    @BeforeEach
    public void setup() {
        trackingServiceMock = mock(TrackingService.class);
        handler = new DispatchTrackingHandler(trackingServiceMock);
    }

    @Test
    public void listen_DispatchPreparing() throws Exception {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(randomUUID());
        handler.listenDispatchPreparing(testEvent);
        verify(trackingServiceMock, times(1)).processDispatchPreparing(testEvent);
    }

    @Test
    public void listen_DispatchPreparingException() throws Exception {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(randomUUID());
        doThrow(new RuntimeException("Service failure")).when(trackingServiceMock).processDispatchPreparing(testEvent);

        handler.listenDispatchPreparing(testEvent);

        verify(trackingServiceMock, times(1)).processDispatchPreparing(testEvent);
    }

    @Test
    public void listen_DispatchCompleted() throws Exception {
        DispatchCompleted testEvent = TestEventData.buildDispatchCompletedEvent(randomUUID(), LocalDate.now().toString());
        handler.listenDispatchCompleted(testEvent);
        verify(trackingServiceMock, times(1)).processDispatched(testEvent);
    }

    @Test
    public void listen_DispatchCompletedThrowsException() throws Exception {
        DispatchCompleted testEvent = TestEventData.buildDispatchCompletedEvent(randomUUID(), LocalDate.now().toString());
        doThrow(new RuntimeException("Service failure")).when(trackingServiceMock).processDispatched(testEvent);

        handler.listenDispatchCompleted(testEvent);

        verify(trackingServiceMock, times(1)).processDispatched(testEvent);
    }
}
