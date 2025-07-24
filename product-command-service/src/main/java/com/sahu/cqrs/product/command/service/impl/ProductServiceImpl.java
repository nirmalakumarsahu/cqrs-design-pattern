package com.sahu.cqrs.product.command.service.impl;

import com.sahu.cqrs.common.constant.EventType;
import com.sahu.cqrs.common.dto.ProductRequestDTO;
import com.sahu.cqrs.common.dto.ProductResponseDTO;
import com.sahu.cqrs.common.event.ProductEvent;
import com.sahu.cqrs.product.command.entity.Product;
import com.sahu.cqrs.product.command.event.producer.EventProducer;
import com.sahu.cqrs.product.command.repository.ProductRepository;
import com.sahu.cqrs.product.command.service.ProductService;
import com.sahu.cqrs.product.command.service.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final EventProducer eventProducer;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequestDTO, product);
        product = productRepository.save(product);

        ProductResponseDTO productResponseDTO = ProductUtil.productResponseDTO(product);

        //Produce event to the Kafka
        eventProducer.produceProductEvent(EventType.CREATED, productResponseDTO);

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        return productRepository.findById(id).map(product -> {
            BeanUtils.copyProperties(productRequestDTO, product);
            product.setId(id);
            product = productRepository.save(product);

            ProductResponseDTO productResponseDTO = ProductUtil.productResponseDTO(product);

            //Produce event to the Kafka
            eventProducer.produceProductEvent(EventType.PUT_UPDATED, productResponseDTO);

            return productResponseDTO;
        }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(
                product -> {
                    productRepository.delete(product);
                    ProductResponseDTO productResponseDTO = ProductUtil.productResponseDTO(product);

                    //Produce event to the Kafka
                    eventProducer.produceProductEvent(EventType.DELETED, productResponseDTO);
                },
                () -> {
                    throw new RuntimeException("Product not found with id: " + id);
                }
        );
    }

}
