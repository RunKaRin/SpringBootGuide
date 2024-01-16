package org.springboot.valid_exception.service;

import org.springboot.valid_exception.data.dto.ProductDto;
import org.springboot.valid_exception.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
