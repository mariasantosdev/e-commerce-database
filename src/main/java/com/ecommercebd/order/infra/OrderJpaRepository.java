package com.ecommercebd.order.infra;

import com.ecommercebd.order.domain.Order;
import com.ecommercebd.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long>, OrderRepository {

}