package com.clothesstore.dto;

import java.io.Serializable;
import java.util.List;

public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String productId;
	private String name;
	private String description;
	private Float price;
	private Float salePrice;
	private Float discount;
	private List<ImagesDto> images;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public List<ImagesDto> getImages() {
		return images;
	}

	public void setImages(List<ImagesDto> images) {
		this.images = images;
	}
	
	public void calculateSalePrice() {
		setSalePrice(getPrice()*(1-getDiscount()/100));
	}
}
