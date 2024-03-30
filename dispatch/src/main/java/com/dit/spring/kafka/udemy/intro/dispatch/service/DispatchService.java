package com.dit.spring.kafka.udemy.intro.dispatch.service;

import com.dit.spring.kafka.udemy.intro.commondto.message.DispatchPreparing;
import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderCreated;
import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.dit.spring.kafka.udemy.intro.commondto.enums.TopicEnum.DISPATCH_TRACKING;
import static com.dit.spring.kafka.udemy.intro.commondto.enums.TopicEnum.ORDER_DISPATCHED;
import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DispatchService {

    private static final UUID APPLICATION_ID = randomUUID();

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void  process(String key, OrderCreated orderCreated) throws Exception {
        log.info("DispatchService is processing ");

        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .proccessedById(APPLICATION_ID)
                .notes("Dispatched: " + orderCreated.getItem())
                .build();
        // get() to make it Synchronous
        kafkaProducer.send(ORDER_DISPATCHED.getTopicName(), key, orderDispatched).get();

        DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
                .orderId(orderCreated.getOrderId())
                .build();
        // Send dispatch preparing message
        kafkaProducer.send(DISPATCH_TRACKING.getTopicName(), key, dispatchPreparing);

        log.info("Sent messages: key: {} - orderId: {} - proccessedById: {}", key, orderCreated.getOrderId(), APPLICATION_ID);
    }
}
