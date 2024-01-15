package org.springboot.relationship.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "provider")
public class Provider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER) // ProviderRepositoryTest 테스트1,2 사용가능
//    @OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST) // cascadeTest 사용가능 orphanRemovalTest remove 불가능
    @OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST, orphanRemoval = true)
    // cascadeTest 사용가능, orphanRemovalTest remove 확인가능
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();
}
