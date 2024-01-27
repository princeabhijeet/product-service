package com.prince.productservice.dtos;

import lombok.Data;

@Data
public class ProductDto {
	private String productName;
	private Long price;
	private Long quantity;
	
}
