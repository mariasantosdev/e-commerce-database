package com.ecommercebd.product;

import com.ecommercebd.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
class ProductController {
	
	private final Mapper mapper;
	private final ProductRepository productRepository;
	
}
