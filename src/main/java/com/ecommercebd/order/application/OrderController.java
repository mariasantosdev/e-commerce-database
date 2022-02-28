package com.ecommercebd.order.application;

import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.order.domain.OrderRepository;
import com.ecommercebd.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
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
                .map(p -> mapper.map(p, OrderResponse.class)).toList();
    }

    @GetMapping("{orderId}")
    OrderResponse findById(@PathVariable Long orderId){
        return orderRepository.findById(orderId)
                .map(p -> mapper.map(p, OrderResponse.class))
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado."));
    }


    //TODO Cadastrar Order
    //TODO Atualizar Order
    //TODO Delete Order
}
