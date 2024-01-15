package org.springboot.relationship.data.repository.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.relationship.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        List<Product> products = Arrays.asList(
                new Product(1L, "펜", 1000, 100),
                new Product(2L, "펜", 5000, 300),
                new Product(3L, "펜", 1000, 50)
        );

        productRepository.saveAll(products);
    }

    @Test
    void findByNameTest() {
        List<Product> productList = productRepository.findByName("펜");

        for (Product product : productList) {
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
}
