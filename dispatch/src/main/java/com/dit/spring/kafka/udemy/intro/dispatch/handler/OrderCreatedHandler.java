package com.dit.spring.kafka.udemy.intro.dispatch.handler;

import com.dit.spring.kafka.udemy.intro.dispatch.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    @KafkaListener(id = "orderConsumerClient", topics = "order.created", groupId = "dispatch.order.created.consumer")
    public void listen(String payload) {
        log.info("Received message: layload: {}", payload);
        dispatchService.process(payload);
    }
}
