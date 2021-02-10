package com.clothesstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clothesstore.common.ImageDescription;
import com.clothesstore.common.Utils;
import com.clothesstore.dto.ProductDto;
import com.clothesstore.entity.ProductEntity;
import com.clothesstore.repository.ImagesRepository;
import com.clothesstore.repository.ProductRepository;
import com.clothesstore.ui.model.response.ErrorMessages;

@Service
public class ProductService implements IProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImagesRepository imagesRepository;

	@Autowired
	Utils utils;

	@Override
	public ProductDto createProduct(ProductDto productDto) throws Exception {

		if (productDto.getProductId() != null && productRepository.findByProductId(productDto.getProductId()) != null)
			throw new Exception(ErrorMessages.PRODUCT_ALREADY_EXISTS.getErrorMessage());
		else
			productDto.setProductId(utils.generateProducId(5));

		ModelMapper modelMapper = new ModelMapper();

		ProductEntity productEntity = new ProductEntity();
		modelMapper.map(productDto, productEntity);

		ProductEntity storedProduct = productRepository.save(productEntity);
		imagesRepository.saveAll(storedProduct.getImages().stream().map(i -> {
			i.setProduct(storedProduct);
			return i;
		}).collect(Collectors.toList()));

		return modelMapper.map(storedProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductByProductId(String productId) throws Exception {

		ProductEntity productEntity = productRepository.findByProductId(productId);
		if (productEntity == null)
			throw new Exception(ErrorMessages.PRODUCT_DOESNT_EXISTS.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(productEntity, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(String productId, ProductDto productDto) throws Exception {

		ProductDto returnValue = new ProductDto();
		ProductEntity productEntityOpt = productRepository.findByProductId(productId);

		if (productEntityOpt == null)
			throw new Exception(ErrorMessages.PRODUCT_DOESNT_EXISTS.getErrorMessage());
		productEntityOpt.setDescription(productDto.getDescription());

		ProductEntity updatedProduct = productRepository.save(productEntityOpt);
		BeanUtils.copyProperties(updatedProduct, returnValue);

		return returnValue;
	}

	@Override
	public void deleteProduct(String productId) throws Exception {

		ProductEntity productEntityOpt = productRepository.findByProductId(productId);
		if (productEntityOpt == null)
			throw new Exception(ErrorMessages.PRODUCT_DOESNT_EXISTS.getErrorMessage());

		productRepository.delete(productEntityOpt);
	}

	@Override
	public List<ProductDto> getMostSearched() {
		List<ProductEntity> findMostSearched = productRepository.findMostSearched();

		ModelMapper modelMapper = new ModelMapper();

		List<ProductDto> returnValue = findMostSearched.stream().map(p -> {
			p.setImages(
					p.getImages().stream()
							.filter(f -> f.getDescription().equals(ImageDescription.FRONT.name())
									|| f.getDescription().equals(ImageDescription.BACK.name()))
							.collect(Collectors.toList()));
			return modelMapper.map(p, ProductDto.class);
		}).collect(Collectors.toList());
		return returnValue;
	}

	@Override
	public List<ProductDto> getProductsByName(String name, int page, int limit) {

		Pageable pegeableRequest = PageRequest.of(page, limit);

		List<ProductEntity> findMostSearched = productRepository.findByNameContainingIgnoreCase(name, pegeableRequest)
				.getContent();
		ModelMapper modelMapper = new ModelMapper();
		List<ProductDto> returnValue = findMostSearched.stream().map(p -> {
			p.setImages(
					p.getImages().stream()
							.filter(f -> f.getDescription().equals(ImageDescription.FRONT.name())
									|| f.getDescription().equals(ImageDescription.BACK.name()))
							.collect(Collectors.toList()));
			return modelMapper.map(p, ProductDto.class);
		}).collect(Collectors.toList());

		updateSearched(findMostSearched);

		return returnValue;
	}

	private void updateSearched(List<ProductEntity> listProductsSearched) {
		listProductsSearched.forEach(p -> {
			p.setSearched(p.getSearched() == null ? 1 : p.getSearched() + 1);
		});
		productRepository.saveAll(listProductsSearched);
	}

}
