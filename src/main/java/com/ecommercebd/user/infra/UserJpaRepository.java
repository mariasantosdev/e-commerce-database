package com.ecommercebd.user.infra;

import com.ecommercebd.user.domain.User;
import com.ecommercebd.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {

}
