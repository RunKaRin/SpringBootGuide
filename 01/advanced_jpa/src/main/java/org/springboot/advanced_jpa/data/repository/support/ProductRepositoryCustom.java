package org.springboot.advanced_jpa.data.repository.support;

import org.springboot.advanced_jpa.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByName(String name);
}
