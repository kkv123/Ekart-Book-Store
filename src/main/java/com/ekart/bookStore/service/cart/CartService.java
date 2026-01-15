package com.ekart.bookStore.service.cart;

import com.ekart.bookStore.entity.cart.Cart;
import org.springframework.http.ResponseEntity;

public interface CartService {
    public Cart getCartByUserId(String userId);
    public ResponseEntity<String> addItemToCart(String userId, int productId, int quantity);
    public void updateCartItemQuantity(int cartItemId, int quantity);
    public void removeItemFromCart(int cartItemId);


}
