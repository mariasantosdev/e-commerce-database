package com.ecommercebd.product.application;

import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.product.domain.Product;
import com.ecommercebd.product.domain.ProductRepository;
import com.ecommercebd.security.IsAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
class ProductController {
	
	private final Mapper mapper;
	private final ProductRepository productRepository;

	@GetMapping
	List<ProductResponse> findAll() {
		return productRepository.findAll()
				.stream()
				.map(p -> mapper.map(p, ProductResponse.class)).toList();
	}

	@GetMapping("{productId}")
	ProductResponse findById(@PathVariable Long productId) {
		return productRepository.findById(productId)
				.map(p -> mapper.map(p, ProductResponse.class))
				.orElseThrow(() -> new NotFoundException("Produto não encontrado."));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@IsAdmin
	Product create(@RequestBody @Valid NewProductRequest newProductRequest) {
		return productRepository.save(mapper.map(newProductRequest, Product.class));
	}

	@PutMapping("{productId}")
	@IsAdmin
	ProductResponse update(@PathVariable Long productId,
						@RequestBody @Valid NewProductRequest newProductRequest) {
		final Product product = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundException("Produto não encontrado."));

		this.mapper.map(newProductRequest, product);
		this.productRepository.save(product);

		return this.mapper.map(product, ProductResponse.class);
	}

	@DeleteMapping("{productId}")
	@IsAdmin
	void delete(@PathVariable Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundException("Produto não encontrado."));

		productRepository.delete(product);
	}
}
