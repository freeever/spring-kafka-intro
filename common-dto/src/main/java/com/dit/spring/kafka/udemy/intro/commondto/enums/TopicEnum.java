package com.dit.spring.kafka.udemy.intro.commondto.enums;

import lombok.Data;

public enum TopicEnum {
    ORDER_CREATED("order.created"),
    ORDER_DISPATCHED("order.dispatched"),
    DISPATCH_TRACKING("dispatch.tracking"),
    TRACKING_STATUS("tracking.status");

    private String topicName;

    TopicEnum(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
