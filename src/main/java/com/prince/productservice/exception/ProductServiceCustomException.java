package com.prince.productservice.exception;

public class ProductServiceCustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String errorCode;
	
	public ProductServiceCustomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
}
