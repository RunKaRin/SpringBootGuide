package org.springboot.relationship.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springboot.relationship.data.entity.Product;
import org.springboot.relationship.data.entity.QProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    JPAQueryFactory jpaQueryFactory;

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

    @Test
    void queryAnnotationTest() {
        List<Product> productsByName = productRepository.findByName("펜");
        System.out.println("Products by Name: " + productsByName);

        List<Product> productsByNameParam = productRepository.findByNameParam("펜");
        System.out.println("Products by Name (Param): " + productsByNameParam);

        List<Object[]> productsByNameParam2 = productRepository.findByNameParam2("펜");
        System.out.println("Products by Name (Param2):");
        for (Object[] result : productsByNameParam2) {
            System.out.println(Arrays.toString(result));
        }
    }

    private Sort getSort() {
        return Sort.by(Order.asc("price"), Order.desc("stock"));
    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        }
    }

    @Test
    void queryDslTest3() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println("Product Name : " + product);
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product : tupleList) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println("Product Name : " + product.get(qProduct.name));
            System.out.println("Product Price : " + product.get(qProduct.price));
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        }
    }

    @Test
    void queryDslTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println("Product Name : " + product);
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        }
    }

    @Test
    public void auditingTest() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(500);

        Product savedProduct = productRepository.save(product);


        System.out.println("ProductName : " + savedProduct.getName());
        System.out.println("createAt : " + savedProduct.getCreatedAt());
    }
}
