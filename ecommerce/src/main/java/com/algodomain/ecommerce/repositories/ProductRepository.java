package com.algodomain.ecommerce.repositories;

import com.algodomain.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p from Product p where p.name like :keyword")
    List<Product> findByName(String keyword);
}
