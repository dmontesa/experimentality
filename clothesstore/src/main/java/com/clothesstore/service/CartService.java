package com.clothesstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothesstore.common.Utils;
import com.clothesstore.dto.CartDto;
import com.clothesstore.dto.ProductDto;
import com.clothesstore.entity.CartEntity;
import com.clothesstore.entity.ProductEntity;
import com.clothesstore.entity.ProductInCart;
import com.clothesstore.repository.CartRepositoy;
import com.clothesstore.repository.ProductInCartRepositoy;
import com.clothesstore.repository.ProductRepository;
import com.clothesstore.ui.model.response.ErrorMessages;

@Service
public class CartService implements ICartService {

	@Autowired
	Utils utils;

	@Autowired
	CartRepositoy cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductInCartRepositoy productInCart;

	@Override
	public CartDto createCard() {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setCartId(utils.generateCartId(3));

		CartEntity savedEntity = cartRepository.save(cartEntity);

		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(savedEntity, CartDto.class);
	}

	@Override
	public CartDto addProduct(String cartId, String productId) throws Exception {
		Optional<ProductEntity> optProduct = productRepository.findByProductId(productId);
		if (optProduct.isEmpty())
			throw new Exception(ErrorMessages.PRODUCT_DOESNT_EXISTS.getErrorMessage());

		Optional<CartEntity> optCart = cartRepository.findByCartId(cartId);
		if (optCart.isEmpty())
			throw new Exception(ErrorMessages.CART_DOESNT_EXISTS.getErrorMessage());

		CartEntity cartEntity = optCart.get();
		ProductEntity productEntity = optProduct.get();

		ProductInCart entity = new ProductInCart();
		entity.setCart(cartEntity);
		entity.setProduct(productEntity);
		productInCart.save(entity);

		ModelMapper modelMapper = new ModelMapper();
		List<ProductDto> products = new ArrayList<>();
		CartDto cartDto = modelMapper.map(cartEntity, CartDto.class);
		cartEntity.getCart().stream().forEach(cart -> {
			products.add(modelMapper.map(cart.getProduct(), ProductDto.class));
		});
		//products.add(modelMapper.map(productEntity, ProductDto.class));
		
		cartDto.setProducts(products);
		
		return cartDto;
	}

	@Override
	public CartDto getCart(String cartId) throws Exception {
		Optional<CartEntity> optCart = cartRepository.findByCartId(cartId);
		if (optCart.isEmpty())
			throw new Exception(ErrorMessages.CART_DOESNT_EXISTS.getErrorMessage());
		
		ModelMapper modelMapper = new ModelMapper();
		List<ProductDto> products = new ArrayList<>();
		CartEntity cartEntity = optCart.get();
		
		CartDto cartDto = modelMapper.map(cartEntity, CartDto.class);
		cartEntity.getCart().stream().forEach(cart -> {
			products.add(modelMapper.map(cart.getProduct(), ProductDto.class));
		});
		
		cartDto.setProducts(products);
		cartDto.calculateTotal();
		cartDto.calculateTotalDiscount();
		
		return cartDto;
	}

}
