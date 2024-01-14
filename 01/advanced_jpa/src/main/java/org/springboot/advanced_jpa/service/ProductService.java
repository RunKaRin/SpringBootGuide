package org.springboot.advanced_jpa.service;

import org.springboot.advanced_jpa.data.dto.ProductDto;
import org.springboot.advanced_jpa.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
