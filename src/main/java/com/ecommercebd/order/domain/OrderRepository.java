package com.ecommercebd.order.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();
    Order save(Order entity);
    Optional<Order> findById(Long id);

    List<Order> findAllByCustomerEmail(String email);

    Optional<Order> findByIdAndCustomerEmail(Long id, String email);
    void delete(Order entity);

}
