package com.clothesstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "images")
public class ImagesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7053967582474767565L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}
