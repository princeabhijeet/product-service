package com.prince.productservice.service;

import com.prince.productservice.dtos.ProductDto;
import com.prince.productservice.entities.Product;

public interface ProductService {
	Product addproduct(ProductDto productDto);
	Product getProductById(Long productId);
	Product reduceProductQuantity(Long productId, Long quantity);

}
