package com.sahu.cqrs.product.query.controller.rest;

import com.sahu.cqrs.common.dto.ProductResponseDTO;
import com.sahu.cqrs.product.query.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> fetchAllProduct() {
        return ResponseEntity.ok(productService.findAll());
    }

}
