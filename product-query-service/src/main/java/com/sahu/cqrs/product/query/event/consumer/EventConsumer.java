package com.sahu.cqrs.product.query.event.consumer;

import com.sahu.cqrs.common.constant.EventType;
import com.sahu.cqrs.common.dto.ProductResponseDTO;
import com.sahu.cqrs.common.event.ProductEvent;
import com.sahu.cqrs.product.query.entity.Product;
import com.sahu.cqrs.product.query.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final ProductRepository productRepository;

    public void consumeProductEvent(ProductEvent productEvent) {
        if (productEvent.getEventType().equals(EventType.CREATED)){
            addProduct(productEvent.getProductResponseDTO());
        } else if (productEvent.getEventType().equals(EventType.PUT_UPDATED)) {
            updateProduct(productEvent.getProductResponseDTO());
        } else if (productEvent.getEventType().equals(EventType.DELETED)) {
            deleteProduct(productEvent.getProductResponseDTO());
        }
    }

    private void addProduct(ProductResponseDTO productResponseDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productResponseDTO, product);
        productRepository.save(product);
    }

    private void updateProduct(ProductResponseDTO productResponseDTO) {
        productRepository.findById(productResponseDTO.id()).ifPresentOrElse(
            product -> {
                BeanUtils.copyProperties(productResponseDTO, product);
                productRepository.save(product);
            },
            () -> log.warn("Product not found with id: {} to update", productResponseDTO.id())
        );
    }

    private void deleteProduct(ProductResponseDTO productResponseDTO) {
        productRepository.findById(productResponseDTO.id()).ifPresentOrElse(
                productRepository::delete,
            () -> log.warn("Product not found with id: {} to delete", productResponseDTO.id())
        );
    }

}
