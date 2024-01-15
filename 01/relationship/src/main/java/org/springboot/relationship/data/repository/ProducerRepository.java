package org.springboot.relationship.data.repository;

import org.springboot.relationship.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
