package com.sahu.cqrs.product.query.service.impl;

import com.sahu.cqrs.common.dto.ProductResponseDTO;
import com.sahu.cqrs.product.query.repository.ProductRepository;
import com.sahu.cqrs.product.query.service.ProductService;
import com.sahu.cqrs.product.query.service.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> findAll() {
        return ProductUtil.productResponseDTOS(productRepository.findAll());
    }
}
