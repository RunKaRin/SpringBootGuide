package org.springboot.relationship.data.repository.support;

import org.springboot.relationship.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByName(String name);
}
