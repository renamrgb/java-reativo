package com.rgb.reativo.reativo.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Payment {
    String id;
    String userId;
    PaymentStatus status;


    public enum PaymentStatus {
        PENDING,APPOVED
    }
}
