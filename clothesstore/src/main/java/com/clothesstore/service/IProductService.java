package com.clothesstore.service;

import java.util.List;

import com.clothesstore.dto.ProductDto;

public interface IProductService {

	ProductDto createProduct(ProductDto productDto) throws Exception;

	ProductDto getProductByProductId(String id) throws Exception;

	ProductDto updateProduct(String productId, ProductDto productDto) throws Exception;

	void deleteProduct(String id) throws Exception;

	List<ProductDto> getMostSearched();

	List<ProductDto> getProductsByName(String name, int page, int limit);
}
