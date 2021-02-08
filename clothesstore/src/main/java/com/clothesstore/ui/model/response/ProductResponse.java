package com.clothesstore.ui.model.response;

import java.util.List;

public class ProductResponse {

	private String productId;
	private String name;
	private String description;
	private Float price;
	private Float salePrice;
	private Float discount;
	private List<ImagesResponse> images;

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

	public List<ImagesResponse> getImages() {
		return images;
	}

	public void setImages(List<ImagesResponse> images) {
		this.images = images;
	}

}
