package com.dit.spring.kafka.udemy.intro.commondto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchCompleted {
    private UUID orderId;
    private String dispatchedDate;
}
