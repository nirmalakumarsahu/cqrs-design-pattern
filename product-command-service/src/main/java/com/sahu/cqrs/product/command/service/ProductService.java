package com.sahu.cqrs.product.command.service;

import com.sahu.cqrs.common.dto.ProductRequestDTO;
import com.sahu.cqrs.common.dto.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long id);
}
