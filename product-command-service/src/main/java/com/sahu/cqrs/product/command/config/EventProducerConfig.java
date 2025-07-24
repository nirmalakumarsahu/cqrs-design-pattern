package com.sahu.cqrs.product.command.config;

import com.sahu.cqrs.common.event.ProductEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Slf4j
@Configuration
public class EventProducerConfig {

    @Bean
    public Sinks.Many<ProductEvent> productEventSink() {
        log.info("Sink for ProductEvent created");
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<ProductEvent>> productEventProducer(Sinks.Many<ProductEvent> orderEventSink) {
        log.info("ProductEvent pushed to the topic");
        return orderEventSink::asFlux;
    }

}
