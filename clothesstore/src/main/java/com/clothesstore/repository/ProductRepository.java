package com.clothesstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.clothesstore.entity.ProductEntity;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

	Optional<ProductEntity> findByProductId(String productId);

	@Query(nativeQuery = true, value = "SELECT * FROM product p ORDER BY p.searched DESC LIMIT 5")
	List<ProductEntity> findMostSearched();

	Page<ProductEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
