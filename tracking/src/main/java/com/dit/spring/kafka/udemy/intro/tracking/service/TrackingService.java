package com.dit.spring.kafka.udemy.intro.tracking.service;

import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchCompleted;
import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchPreparing;
import com.dit.spring.kafka.udemy.intro.tracking.message.TrackingStatusUpdated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.dit.spring.kafka.udemy.intro.commondto.enums.TopicEnum.TRACKING_STATUS;
import static com.dit.spring.kafka.udemy.intro.tracking.enums.TrackingStatus.DISPATCHED;
import static com.dit.spring.kafka.udemy.intro.tracking.enums.TrackingStatus.PREPARING;

@Slf4j
@RequiredArgsConstructor
@Service
public class TrackingService {

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void processDispatchPreparing(DispatchPreparing dispatchPreparing) {
        log.info("Received DispatchPreparing message : " + dispatchPreparing);

        TrackingStatusUpdated trackingStatusUpdated = TrackingStatusUpdated.builder()
                .orderId(dispatchPreparing.getOrderId())
                .status(PREPARING)
                .build();
        kafkaProducer.send(TRACKING_STATUS.getTopicName(), trackingStatusUpdated);
    }

    public void processDispatched(DispatchCompleted dispatchCompleted) {
        log.info("Received dispatched message : " + dispatchCompleted);

        TrackingStatusUpdated trackingStatusUpdated = TrackingStatusUpdated.builder()
                .orderId(dispatchCompleted.getOrderId())
                .status(DISPATCHED)
                .build();
        kafkaProducer.send(TRACKING_STATUS.getTopicName(), trackingStatusUpdated);
    }
}
