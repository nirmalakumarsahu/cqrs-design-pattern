package com.sahu.cqrs.product.command.controller.rest;

import com.sahu.cqrs.common.dto.ProductRequestDTO;
import com.sahu.cqrs.common.dto.ProductResponseDTO;
import com.sahu.cqrs.product.command.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequestDTO));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequestDTO));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
