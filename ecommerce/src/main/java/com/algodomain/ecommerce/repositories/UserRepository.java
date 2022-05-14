package com.algodomain.ecommerce.repositories;

import com.algodomain.ecommerce.entities.Product;
import com.algodomain.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query( value = "SELECT p.* FROM products p\n" +
                    "WHERE p.id IN (SELECT ci.product_id\n" +
                    "FROM cart_items ci WHERE ci.user_id = :userId)",
            nativeQuery = true
    )
    List<Product> getCartItems(Integer userId);

    Optional<User> findByEmail(String email);

}
