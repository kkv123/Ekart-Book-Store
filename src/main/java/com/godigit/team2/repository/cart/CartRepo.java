package com.godigit.team2.repository.cart;

import com.godigit.team2.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.userId = :userId")
    Cart findByUserId(String userId);
}
