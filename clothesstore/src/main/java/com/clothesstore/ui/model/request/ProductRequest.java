package com.clothesstore.ui.model.request;

import java.util.List;

public class ProductRequest {

	private String name;
	private String productId;
	private String description;
	private Float price;
	private Float discount;
	private List<ImagesRequest> images;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public List<ImagesRequest> getImages() {
		return images;
	}

	public void setImages(List<ImagesRequest> images) {
		this.images = images;
	}

}
