package com.algodomain.ecommerce.services.impl;

import com.algodomain.ecommerce.entities.Product;
import com.algodomain.ecommerce.entities.User;
import com.algodomain.ecommerce.exceptions.ResourceNotFoundException;
import com.algodomain.ecommerce.payload.ProductDto;
import com.algodomain.ecommerce.repositories.ProductRepository;
import com.algodomain.ecommerce.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        Product savedProduct = this.productRepository.save(product);

        return this.modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = this.productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", id));
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(product.getDescription());
        product.setProductImage(productDto.getProductImage());

        Product updatedProduct = this.productRepository.save(product);
        return this.modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        this.productRepository.delete(product);
    }

    @Override
    public List<ProductDto> searchProducts(String keyword) {
        List<Product> products = this.productRepository.findByName("%" + keyword + "%");
        return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}
