package com.dit.spring.kafka.udemy.intro.tracking.handler;

import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchCompleted;
import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchPreparing;
import com.dit.spring.kafka.udemy.intro.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@KafkaListener(id = "dispatchTrackingConsumerClient",
        topics = "dispatch.tracking",
        groupId = "tracking.dispatch.tracking",
        containerFactory = "kafkaListenerContainerFactory"
)
public class DispatchTrackingHandler {

    private final TrackingService trackingService;

    @KafkaHandler
    public void listenDispatchPreparing(DispatchPreparing dispatchPreparing) {
        log.info("Received DispatchPreparing message: payload: orderId={}", dispatchPreparing.getOrderId());
        try {
            trackingService.processDispatchPreparing(dispatchPreparing);
        } catch (Exception e) {
            log.error("Processing DispatchPreparing failure", e);
        }
    }

    @KafkaHandler
    public void listenDispatchCompleted(DispatchCompleted dispatchCompleted) {
        log.info("Received DispatchCompleted message: payload: orderId={}, completedDate={}",
                dispatchCompleted.getOrderId(), dispatchCompleted.getDispatchedDate());
        try {
            trackingService.processDispatched(dispatchCompleted);
        } catch (Exception e) {
            log.error("Processing DispatchCompleted failure", e);
        }
    }
}
