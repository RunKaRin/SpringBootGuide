package org.springboot.jpa.data.repository;

import org.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
