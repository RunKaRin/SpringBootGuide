package org.springboot.relationship.data.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springboot.relationship.data.entity.Producer;
import org.springboot.relationship.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProducerRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProducerRepository producerRepository;

    @Test
    @Transactional
    void relationshipTest() { // N:N 단방향 매핑
        Product product1 = saveProduct("가가가", 1100, 1000);
        Product product2 = saveProduct("나나나", 1500, 2000);
        Product product3 = saveProduct("다다다", 1300, 1500);

        Producer producer1 = saveProducer("○ ○ ○");
        Producer producer2 = saveProducer("● ● ●");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product2);
        producer2.addProduct(product3);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        System.out.println(producerRepository.findById(1L).get().getProducts());
    }

    @Test
    @Transactional
    void relationshipTest2() { // N:N 양방향 매핑
        Product product1 = saveProduct("가가가", 1100, 1000);
        Product product2 = saveProduct("나나나", 1500, 2000);
        Product product3 = saveProduct("다다다", 1300, 1500);

        Producer producer1 = saveProducer("○ ○ ○");
        Producer producer2 = saveProducer("● ● ●");

        producer1.addProduct(product1);
        producer1.addProduct(product2);
        producer2.addProduct(product2);
        producer2.addProduct(product3);
        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        product1.addProducer(producer1);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);
        productRepository.saveAll(Lists.newArrayList(product1, product2));

        System.out.println("products : " +
                producerRepository.findById(1L).get().getProducts());
        System.out.println("producers : " +
                productRepository.findById(2L).get().getProducers());
    }

    private Product saveProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return productRepository.save(product);
    }

    private Producer saveProducer(String name) {
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);
    }
}
