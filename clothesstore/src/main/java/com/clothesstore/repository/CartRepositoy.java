package com.clothesstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.clothesstore.entity.CartEntity;

public interface CartRepositoy extends CrudRepository<CartEntity, Long>{

	CartEntity findByCartId(String cartId);
}
