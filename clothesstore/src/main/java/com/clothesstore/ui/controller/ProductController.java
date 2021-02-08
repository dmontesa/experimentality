package com.clothesstore.ui.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothesstore.dto.ProductDto;
import com.clothesstore.service.IProductService;
import com.clothesstore.ui.model.request.ProductRequest;
import com.clothesstore.ui.model.response.ErrorMessages;
import com.clothesstore.ui.model.response.OperationStatusModel;
import com.clothesstore.ui.model.response.ProductResponse;
import com.clothesstore.ui.model.response.RequestOperationName;
import com.clothesstore.ui.model.response.RequestOperationStatus;

/**
 * Controller para los productos
 * Permite consultar los productos más buscados,
 * agregar nuevos productos y consultar detalles de productos
 * @author daniel
 *
 */
@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	IProductService productService;

	/**
	 * Endpoint que permite consultar los 5 productos más buscados
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/most-searched")
	public List<ProductResponse> getMostSearched() throws Exception {

		List<ProductDto> productDtoList = productService.getMostSearched();
		ModelMapper modelMapper = new ModelMapper();
		List<ProductResponse> productResponse = productDtoList.stream().map(p -> {
			return modelMapper.map(p, ProductResponse.class);
		}).collect(Collectors.toList());

		return productResponse;
	}

	/**
	 * Enpoint que permite buscar productos por nombre
	 * La búsqueda puede ser paginada, sino se envian los parametros
	 * page y limit se les da un valor por defecto de 0 y 10 respectivamente
	 * @param productRequest
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	@GetMapping
	public List<ProductResponse> getProductsByName(@RequestBody ProductRequest productRequest,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) throws Exception {

		if (productRequest.getName() == null || productRequest.getName().equals(""))
			throw new Exception(ErrorMessages.PRODUCT_NAME_NEEDED.getErrorMessage());

		List<ProductDto> productDtoList = productService.getProductsByName(productRequest.getName(), page, limit);
		ModelMapper modelMapper = new ModelMapper();
		List<ProductResponse> productResponse = productDtoList.stream().map(p -> {
			return modelMapper.map(p, ProductResponse.class);
		}).collect(Collectors.toList());

		return productResponse;

	}

	/**
	 * Endpoint que permite consultar los detalles de un producto especifico
	 * por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/{id}")
	public ProductResponse getProduct(@PathVariable String id) throws Exception {

		ProductDto productDto = productService.getProductByProductId(id);
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(productDto, ProductResponse.class);
	}
	
	/**
	 * Endpoint para crear nuevos productos
	 * @param productRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest) throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		productDto.calculateSalePrice();

		ProductDto productCreated = productService.createProduct(productDto);

		return modelMapper.map(productCreated, ProductResponse.class);
	}

	/**
	 * Endpoint que permite actualizar productos
	 * @param id
	 * @param productRequest
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/{id}")
	public ProductResponse updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest)
			throws Exception {

		ProductResponse productResponse = new ProductResponse();

		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(productRequest, productDto);

		ProductDto productCreated = productService.updateProduct(id, productDto);
		BeanUtils.copyProperties(productCreated, productResponse);

		return productResponse;
	}

	/**
	 * Endpoint que permite realizar un borrado lógico de un producto
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteProduct(@PathVariable String id) throws Exception {

		productService.deleteProduct(id);

		OperationStatusModel returnValue = new OperationStatusModel(RequestOperationName.DELETE.name(),
				RequestOperationStatus.SUCCESS.name());

		return returnValue;
	}
}
