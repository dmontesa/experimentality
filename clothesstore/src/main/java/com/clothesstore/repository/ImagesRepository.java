package com.clothesstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.clothesstore.entity.ImagesEntity;

public interface ImagesRepository extends CrudRepository<ImagesEntity, Long> {
	
}
