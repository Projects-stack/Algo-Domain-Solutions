package com.algodomain.ecommerce.services;

import com.algodomain.ecommerce.payload.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Integer id);

    ProductDto updateProduct(ProductDto productDto, Integer id);

    void deleteProduct(Integer id);

    List<ProductDto> searchProducts(String keyword);

}
