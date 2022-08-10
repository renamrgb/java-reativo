package com.rgb.reativo.reativo.models;

import lombok.Data;

@Data
public class PubSubMessage {
    String key;
    String value;
}
