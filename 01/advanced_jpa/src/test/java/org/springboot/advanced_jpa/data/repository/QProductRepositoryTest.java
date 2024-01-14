package org.springboot.advanced_jpa.data.repository;

import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.advanced_jpa.data.entity.Product;
import org.springboot.advanced_jpa.data.entity.QProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QProductRepositoryTest {

    @Autowired
    QProductRepository qProductRepository;

    @BeforeEach
    void setUp() {
        List<Product> products = Arrays.asList(
                new Product(1L, "펜", 1000, 100, LocalDateTime.now(), LocalDateTime.now()),
                new Product(2L, "노트", 5000, 150, LocalDateTime.now(), LocalDateTime.now()),
                new Product(3L, "지우개", 1000, 50, LocalDateTime.now(), LocalDateTime.now())
        );

        qProductRepository.saveAll(products);
    }

    @Test
    public void queryDSLTest1() {
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000, 2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }

    @Test
    public void queryDSLTest2() {
        QProduct qProduct = QProduct.product;
        Iterable<Product> productList = qProductRepository.findAll(qProduct.name.contains("펜")
                .and(qProduct.price.between(550, 1500)));

        for (Product product : productList) {
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
}
