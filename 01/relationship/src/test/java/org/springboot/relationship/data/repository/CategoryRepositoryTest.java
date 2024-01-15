package org.springboot.relationship.data.repository;

import org.junit.jupiter.api.Test;
import org.springboot.relationship.data.entity.Category;
import org.springboot.relationship.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest() { // 1:N 매핑
        Product product = new Product();
        product.setName("펜");
        product.setPrice(2000);
        product.setStock(100);
        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);
        categoryRepository.save(category);

        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for (Product foundProduct : products) {
            System.out.println(foundProduct);
        }
    }
}
