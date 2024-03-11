package com.dit.spring.kafka.udemy.intro.dispatch.handler;

import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderCreated;
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

    @KafkaListener(id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderCreated orderCreated) {
        log.info("Received message: payload: orderId={}, item={}", orderCreated.getOrderId(), orderCreated.getItem());
        dispatchService.process(orderCreated);
    }
}
