package org.springboot.advanced_jpa.data.repository;

import org.junit.jupiter.api.Test;
import org.springboot.advanced_jpa.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.time.LocalDateTime;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest() {
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(1000);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product3);

        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"))));
        System.out.println(productRepository.findByName("펜", Sort.by(Order.desc("stock"))));
        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock"))));
        System.out.println(productRepository.findByName("펜", getSort()));

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));
        System.out.println(productPage.getContent());
    }

    private Sort getSort() {
        return Sort.by(Sort.Order.asc("price"), Sort.Order.desc("stock"));
    }
}
