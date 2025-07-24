package com.sahu.cqrs.product.command.service.util;

import com.sahu.cqrs.common.dto.ProductResponseDTO;
import com.sahu.cqrs.product.command.entity.Product;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProductUtil {

    public ProductResponseDTO productResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    public List<ProductResponseDTO> productResponseDTOS(List<Product> products) {
        return products.stream().map(ProductUtil::productResponseDTO).toList();
    }

}
