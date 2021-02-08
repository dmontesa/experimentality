package com.clothesstore.ui.model.response;

import java.util.List;

public class CartResponse {
	private String cartId;
	private float total;
	private float totalDiscount;
	private List<ProductResponse> products;

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

}
