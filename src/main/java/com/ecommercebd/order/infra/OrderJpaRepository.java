package com.ecommercebd.order.infra;

import com.ecommercebd.order.domain.Order;
import com.ecommercebd.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long>, OrderRepository {

   @EntityGraph(attributePaths = {"customer"})
    List<Order> findAll();


}