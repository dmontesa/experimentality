package com.clothesstore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "product_in_cart")
public class ProductInCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7308217483187577799L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartEntity cart;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

}
