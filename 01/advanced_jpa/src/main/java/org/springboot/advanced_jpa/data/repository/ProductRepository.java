package org.springboot.advanced_jpa.data.repository;

import org.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
