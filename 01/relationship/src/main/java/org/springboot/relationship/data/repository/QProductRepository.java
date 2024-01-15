package org.springboot.relationship.data.repository;

import org.springboot.relationship.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<Product, Long>,
        QuerydslPredicateExecutor<Product> {
}
