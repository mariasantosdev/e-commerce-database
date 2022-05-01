package com.ecommercebd.order.application;

import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.order.domain.Order;
import com.ecommercebd.order.domain.OrderRepository;
import com.ecommercebd.plan.application.BusinessException;
import com.ecommercebd.plan.domain.Plan;
import com.ecommercebd.plan.domain.PlanRepository;
import com.ecommercebd.security.IsClientOrAdmin;
import com.ecommercebd.user.domain.User;
import com.ecommercebd.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@IsClientOrAdmin
public class MyOrderController {
    private final Mapper mapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    @GetMapping("{orderId}")
    OrderResponse findById(@PathVariable Long orderId, @AuthenticationPrincipal UserDetails userDetails){
        return orderRepository.findByIdAndCustomerEmail(orderId, userDetails.getUsername())
                .map(p -> mapper.map(p, OrderResponse.class))
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado."));
    }

    @GetMapping("/{orders}")
    List<OrderResponse> findAll(@AuthenticationPrincipal UserDetails userDetails){
        return orderRepository.findAllByCustomerEmail(userDetails.getUsername())
                .stream()
                .map(p -> mapper.map(p, OrderResponse.class)).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderResponse create(@RequestBody @Valid NewMyOrderRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        Order order = mapper.map(request, Order.class);

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new BusinessException(String.format("Usuário não encontrado")));

        List<Plan> plans = new ArrayList<>();
        for (Plan plan : order.getPlans()) {
            Plan planFound = planRepository.findById(plan.getId())
                    .orElseThrow(() -> new BusinessException(String.format("Plano não encontrado", plan.getId())));

            plans.add(planFound);
        }

        order.setCustomer(user);
        order.setPlans(plans);

        orderRepository.save(order);
        return this.mapper.map(order, OrderResponse.class);

    }

}
