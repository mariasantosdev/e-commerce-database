package com.ecommercebd.order;

import com.ecommercebd.plan.domain.Plan;
import com.ecommercebd.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subtotal;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", 
            foreignKey = @ForeignKey(name = "fk_order_customer_id"))
    private User customer;
    @ManyToMany
    @JoinTable(name = "order_plans",
            joinColumns = @JoinColumn(name = "order_id", 
                    foreignKey = @ForeignKey(name = "fk_order_plans_order_id")),
            inverseJoinColumns = @JoinColumn(name = "plan_id", 
                    foreignKey = @ForeignKey(name = "fk_order_plans_plan_id")))
    private List<Plan> plans = new ArrayList<>();
}
