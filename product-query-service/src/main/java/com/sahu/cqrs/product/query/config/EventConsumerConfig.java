package com.sahu.cqrs.product.query.config;

import com.sahu.cqrs.common.event.ProductEvent;
import com.sahu.cqrs.product.query.event.consumer.EventConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class EventConsumerConfig {

    private final EventConsumer eventConsumer;

    @Bean
    public Consumer<ProductEvent> productEventConsumer() {
        return eventConsumer::consumeProductEvent;
    }

}
