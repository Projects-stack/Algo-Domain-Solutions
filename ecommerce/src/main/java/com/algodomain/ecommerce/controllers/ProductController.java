package com.algodomain.ecommerce.controllers;

import com.algodomain.ecommerce.payload.ProductDto;
import com.algodomain.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('Seller')")
    @PostMapping("")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto createdProduct = this.productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('Seller')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,
                                                    @PathVariable("id") Integer id) {
        ProductDto updatedProduct = this.productService.updateProduct(productDto, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('Seller')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(Map.of("message", "Product Deleted Successfully"), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProductByTitle(@PathVariable("keyword") String keyword) {
        List<ProductDto> productDtos = new ArrayList<>(this.productService.searchProducts(keyword));
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

}
