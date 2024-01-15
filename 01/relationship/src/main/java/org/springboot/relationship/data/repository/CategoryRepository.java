package org.springboot.relationship.data.repository;

import org.springboot.relationship.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
