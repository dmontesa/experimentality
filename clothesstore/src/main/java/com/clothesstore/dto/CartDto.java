package com.clothesstore.dto;

import java.io.Serializable;
import java.util.List;

public class CartDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5091964689105466428L;

	private long id;
	private String cartId;
	private List<ProductDto> products;
	private float total;
	private float totalDiscount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
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
	
	public void calculateTotal() {
		setTotal((float) getProducts().stream().mapToDouble(p->p.getPrice()).sum());
	}

	public void calculateTotalDiscount() {
		setTotalDiscount((float) getProducts().stream().mapToDouble(p->p.getSalePrice()).sum());
	}
}
