package com.clothesstore.service;

import com.clothesstore.dto.CartDto;

public interface ICartService {

	CartDto createCard();

	CartDto addProduct(String cartId, String productId) throws Exception;

	CartDto getCart(String cartId) throws Exception;

}
