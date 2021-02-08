package com.clothesstore.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.clothesstore.entity.CartEntity;

public interface CartRepositoy extends CrudRepository<CartEntity, Long>{

	Optional<CartEntity> findByCartId(String cartId);
}
