package com.clothesstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.clothesstore.entity.ProductInCart;

public interface ProductInCartRepositoy extends CrudRepository<ProductInCart, Long>{

}
