package com.prince.productservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prince.productservice.dtos.ProductDto;
import com.prince.productservice.entities.Product;
import com.prince.productservice.exception.ProductServiceCustomException;
import com.prince.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addproduct(ProductDto productDto) {
		Product product = Product.builder()
				.productName(productDto.getProductName())
				.price(productDto.getPrice())
				.quantity(productDto.getQuantity())
				.build();
		Product savedProduct = productRepository.save(product);
		log.info("ProductId {} successfully saved in database!", savedProduct.getProductId());
		return savedProduct;
	}

	@Override
	public Product getProductById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		log.info("ProductId {} found in database: {}", productId, optionalProduct.isPresent());
		return optionalProduct.isPresent() ? optionalProduct.get() : null;
	}

	@Override
	public Product reduceProductQuantity(Long productId, Long quantity) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException("ProductId " + productId + "not found in database!", "PRODUCT_UNAVAILABLE"));
		if(product.getQuantity() < quantity) {
			log.error("Insufficient product quantity: AvailableQty: {} | OrderedQty: {}", product.getQuantity(), quantity);
			throw new ProductServiceCustomException("Insufficient product quantity: AvailableQty: " + product.getQuantity() + " | OrderedQty: "+ quantity, "INSUFFICIENT_PRODUCT_QUANTITY");
		}
		product.setQuantity(product.getQuantity() - quantity);
		Product updatedProduct = productRepository.save(product);
		log.info("Product quantity successfully reduced to {} for productId {}", product.getQuantity(), productId);
		return updatedProduct;
	}
	

}
