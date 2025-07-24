package com.sahu.cqrs.product.query.service;

import com.sahu.cqrs.common.dto.ProductRequestDTO;
import com.sahu.cqrs.common.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> findAll();
}
