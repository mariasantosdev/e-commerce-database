package com.ecommercebd.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    User save(User entity);
    Optional<User> findById(Long id);
    void delete(User entity);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
