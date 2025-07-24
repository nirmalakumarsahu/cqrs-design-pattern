package com.sahu.cqrs.product.command.repository;

import com.sahu.cqrs.product.command.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
