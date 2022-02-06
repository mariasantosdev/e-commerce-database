package com.ecommercebd.order;

import com.ecommercebd.plan.domain.Plan;
import com.ecommercebd.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subtotal;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;
    @ManyToMany
    private List<Plan> plans = new ArrayList<>();
}
