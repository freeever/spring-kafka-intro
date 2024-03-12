package com.dit.spring.kafka.udemy.intro.tracking.handler;

import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchPreparing;
import com.dit.spring.kafka.udemy.intro.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DispatchTrackingHandler {

    private final TrackingService trackingService;

    @KafkaListener(id = "dispatchTrackingConsumerClient",
            topics = "dispatch.tracking",
            groupId = "tracking.dispatch.tracking",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing dispatchPreparing) {
        log.info("Received DispatchPreparing message: payload: orderId={}", dispatchPreparing.getOrderId());
        try {
            trackingService.process(dispatchPreparing);
        } catch (Exception e) {
            log.error("Processing DispatchPreparing failure", e);
        }
    }
}
