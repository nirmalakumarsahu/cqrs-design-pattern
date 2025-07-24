package com.sahu.cqrs.common.event;

import com.sahu.cqrs.common.constant.EventType;
import com.sahu.cqrs.common.dto.ProductResponseDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private EventType eventType;
    private ProductResponseDTO productResponseDTO;
}