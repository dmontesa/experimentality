package com.clothesstore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "cart")
public class CartEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3603254733308627068L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, unique = true)
	private String cartId;
	
	@Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	Date deletionDate;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<ProductInCart> cart;

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeletionDate() {
		return deletionDate;
	}

	public void setDeletionDate(Date deletionDate) {
		this.deletionDate = deletionDate;
	}

	public List<ProductInCart> getCart() {
		return cart;
	}

	public void setCart(List<ProductInCart> cart) {
		this.cart = cart;
	}

}
