package com.dit.spring.kafka.udemy.intro.tracking.message;

import com.dit.spring.kafka.udemy.intro.tracking.enums.TrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingStatusUpdated {
    private UUID orderId;
    private TrackingStatus status;
}
