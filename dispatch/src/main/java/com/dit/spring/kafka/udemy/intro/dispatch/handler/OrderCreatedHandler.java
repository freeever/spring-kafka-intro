package com.dit.spring.kafka.udemy.intro.dispatch.handler;

import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderCreated;
import com.dit.spring.kafka.udemy.intro.dispatch.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    @KafkaListener(id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer2",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(@Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key,
                       OrderCreated orderCreated) {
        log.info("Received message: partition={}, key={}", partition, orderCreated.getOrderId(), orderCreated.getItem());
        log.info("================= partition={}, key={}", partition, key);
        try {
            dispatchService.process(key, orderCreated);
        } catch (Exception e) {
            log.error("Processing failure", e);
        }
    }
}
