package com.sahu.cqrs.product.query.repository;

import com.sahu.cqrs.product.query.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
