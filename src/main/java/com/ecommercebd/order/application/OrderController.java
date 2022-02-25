package com.ecommercebd.order.application;

import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.order.domain.OrderRepository;
import com.ecommercebd.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final Mapper mapper;

    @GetMapping
    List<OrderResponse> findAll(){
        return orderRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, OrderResponse.class)).toList();
    }

    //TODO Listar Order
    //TODO Cadastrar Order
    //TODO Atualizar Order
    //TODO Delete Order
}
