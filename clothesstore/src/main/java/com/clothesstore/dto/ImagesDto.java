package com.clothesstore.dto;

import java.io.Serializable;

public class ImagesDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3874121979777862939L;
	private long id;
	private String description;
	private String url;
	private ProductDto product;

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

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

}
