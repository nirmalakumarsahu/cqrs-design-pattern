package com.sahu.cqrs.product.command.event.producer;

import com.sahu.cqrs.common.constant.EventType;
import com.sahu.cqrs.common.dto.ProductResponseDTO;
import com.sahu.cqrs.common.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventProducer {

    private final Sinks.Many<ProductEvent> orderEventSink;

    public void produceProductEvent(EventType eventType, ProductResponseDTO productResponseDTO) {
        log.info("Publishing ProductEvent for order: {}", productResponseDTO.id());
        try {
            ProductEvent productEvent = new ProductEvent(eventType, productResponseDTO);

            // Publish the event to the sink
            orderEventSink.tryEmitNext(productEvent);

            log.info("ProductEvent ready for publish: {}", productResponseDTO.id());
        }
        catch (Exception e) {
            log.error("Failed to publish ProductEvent for order: {}. Error: {}", productResponseDTO.id(), e.getMessage());
        }
    }

}
