package org.springboot.relationship.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@ToString(callSuper = true)
@NoArgsConstructor   // 파라미터가 없는 기본 생성자 생성
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    public Product(Long number, String name, Integer price, Integer stock) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
