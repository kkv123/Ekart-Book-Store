package com.ekart.bookStore.repository.cart;

import com.ekart.bookStore.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    @Query("select c from CartItem c where c.cart.cart_id = :cartId")
    List<CartItem> findByCartId(int cartId);
}
