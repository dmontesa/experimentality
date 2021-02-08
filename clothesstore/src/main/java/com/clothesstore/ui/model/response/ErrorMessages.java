package com.clothesstore.ui.model.response;

public enum ErrorMessages {
	
	PRODUCT_ALREADY_EXISTS("Product already exists."),
	PRODUCT_DOESNT_EXISTS("Product doesn't exists."),
	PRODUCT_NAME_NEEDED("A product name is needed."),
	CART_DOESNT_EXISTS("Cart doesn't exists.");

	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
