package com.godigit.team2.controller.cart;

import com.godigit.team2.entity.cart.Cart;
import com.godigit.team2.service.cart.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-kart/cart")
public class CartController {


    private CartServiceImpl cartService;

    @Autowired
    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestParam String userId, @RequestParam int productId, @RequestParam int quantity) {
        return cartService.addItemToCart(userId, productId, quantity);
    }

    @PutMapping("/update/{cartItemId}")
    public String updateCartItemQuantity(@PathVariable int cartItemId, @RequestParam int quantity) {
        cartService.updateCartItemQuantity(cartItemId, quantity);
        return "Item updated";
    }

    @DeleteMapping("/remove/{cartItemId}")
    public String removeItemFromCart(@PathVariable int cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return "Item removed from the cart";
    }
}
