package com.clothesstore.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothesstore.dto.CartDto;
import com.clothesstore.service.ICartService;
import com.clothesstore.ui.model.request.CartRequest;
import com.clothesstore.ui.model.response.CartResponse;

/**
 * Controlloer para el carrito de comprar
 * permite crear y consultar carritos de compras
 * al igual que agregar productos al mismo
 * @author danie
 *
 */
@RestController
@RequestMapping("api/carts")
public class CartController {

	@Autowired
	ICartService cartService;

	@PostMapping
	public CartResponse createCard() {

		CartDto cartDto = cartService.createCard();
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(cartDto, CartResponse.class);
	}

	@PutMapping(path = "/{id}")
	public CartResponse addProduct(@PathVariable String id, @RequestBody CartRequest cartRequest) throws Exception {
		CartDto cartDto = cartService.addProduct(id, cartRequest.getProductId());
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(cartDto, CartResponse.class);
	}

	@GetMapping(path = "/{id}")
	public CartResponse getCart(@PathVariable String id) throws Exception {
		CartDto cartDto = cartService.getCart(id);
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(cartDto, CartResponse.class);
	}
}
