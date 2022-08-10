package com.rgb.reativo.reativo.controllers;

import com.rgb.reativo.reativo.models.Payment;
import com.rgb.reativo.reativo.repositories.PaymentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

@RestController
@RequestMapping(value = "payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentRepository paymentRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Payment> createPayment(@RequestBody final NewPaymentInput input){
        final String userId = input.getUserId();

        log.info("Payment to be processed {}", userId);
        return this.paymentRepository.createPayment(userId)
                .doOnNext(next -> log.info("Payment processed {}", userId));
    }

    @Data
    public static class NewPaymentInput{
        private String userId;
    }
}
