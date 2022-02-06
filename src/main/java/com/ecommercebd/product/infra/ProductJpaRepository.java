package com.ecommercebd.product.infra;

import com.ecommercebd.product.domain.ProductRepository;
import com.ecommercebd.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductJpaRepository extends JpaRepository<Product, Long>, ProductRepository {

}
