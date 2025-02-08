package com.godigit.team2.service.cart;

import com.godigit.team2.entity.cart.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {
    public Cart getCartByUserId(String userId);
    public ResponseEntity<String> addItemToCart(String userId, int productId, int quantity);
    public void updateCartItemQuantity(int cartItemId, int quantity);
    public void removeItemFromCart(int cartItemId);


}
