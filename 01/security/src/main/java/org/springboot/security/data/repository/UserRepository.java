package org.springboot.security.data.repository;

import org.springboot.security.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User getByUid(String uid);
}
