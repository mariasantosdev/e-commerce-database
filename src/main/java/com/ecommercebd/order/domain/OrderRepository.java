package com.ecommercebd.order.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();
    Order save(Order entity);
    Optional<Order> findById(Long id);
    void delete(Order entity);

}
