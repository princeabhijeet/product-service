package com.prince.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prince.productservice.dtos.ProductDto;
import com.prince.productservice.entities.Product;
import com.prince.productservice.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
		Product product = productService.addproduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
		
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		Product product = productService.getProductById(productId);
		return null!=product ? ResponseEntity.status(HttpStatus.OK).body(product) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/product/reduce-product-quantity/{productId}/{quantity}")
	public ResponseEntity<Product> reduceProductQuantity(@PathVariable Long productId, @PathVariable Long quantity) {
		Product product = productService.reduceProductQuantity(productId, quantity);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

}
