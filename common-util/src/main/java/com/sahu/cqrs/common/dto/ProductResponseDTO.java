package com.sahu.cqrs.common.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponseDTO (
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock
){
    
}
