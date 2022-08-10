package com.rgb.reativo.reativo.repositories;

import com.rgb.reativo.reativo.models.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentRepository {
    private final Database database;

    public Mono<Payment> createPayment(final String userId) {
        final Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .status(Payment.PaymentStatus.PENDING)
                .build();

        return Mono.fromCallable(() -> {
                    log.info("Saving payment transaction for user {}", userId);
                    return this.database.save(userId, payment);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(next -> log.info("Payment received {}", next.getUserId()));
    }

}
