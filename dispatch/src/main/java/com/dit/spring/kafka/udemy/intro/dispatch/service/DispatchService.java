package com.dit.spring.kafka.udemy.intro.dispatch.service;

import com.dit.spring.kafka.udemy.intro.dispatch.message.DispatchPreparing;
import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderCreated;
import com.dit.spring.kafka.udemy.intro.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DispatchService {

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    private static final String DISPATCH_PREPARING_TOPIC = "dispatch.preparing";

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(OrderCreated orderCreated) throws Exception {
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .build();
        // get() to make it Synchronous
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();

        DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
                .orderId(orderCreated.getOrderId())
                .build();
        // Send dispatch preparing message
        kafkaProducer.send(DISPATCH_PREPARING_TOPIC, dispatchPreparing);
    }
}
