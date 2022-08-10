package com.rgb.reativo.reativo;

import com.rgb.reativo.reativo.models.PubSubMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Sinks;

@SpringBootApplication
public class ReativoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReativoApplication.class, args);
    }

    public Sinks.Many<PubSubMessage> sink() {
        return Sinks.many().multicast()
                .onBackpressureBuffer();
    }
}
