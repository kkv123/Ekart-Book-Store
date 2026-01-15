package com.ekart.bookStore.service.cart;

import com.ekart.bookStore.entity.cart.Cart;
import com.ekart.bookStore.entity.cart.CartItem;
import com.ekart.bookStore.entity.product.Product;
import com.ekart.bookStore.repository.cart.CartItemRepo;
import com.ekart.bookStore.repository.cart.CartRepo;
import com.ekart.bookStore.repository.product.ProductRepo;
import com.ekart.bookStore.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartRepo cartRepo;

    private ProductRepo productRepo;

    private CartItemRepo cartItemRepo;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    public CartServiceImpl(CartRepo cartRepo, ProductRepo productRepo, CartItemRepo cartItemRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    public Cart getCartByUserId(String userId) {
        return cartRepo.findByUserId(userId);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addItemToCart(String userId, int productId, int quantity) {
        if(userRepo.findByUser_id(userId)==null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setCreatedAt(LocalDateTime.now());
            cart = cartRepo.save(cart);
        }
        if(productRepo.findById(productId)==null){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        Product product = productRepo.findById(productId).get();
        List<CartItem> cartItems= cartItemRepo.findByCartId(cart.getCart_id());
        if(cartItems!=null){
            CartItem cartItem=null;
            for(CartItem cartItem1: cartItems){
                if(cartItem1.getProduct().getProduct_id()==productId){
                    cartItem =cartItem1;
                    cartItem.setQuantity(cartItem.getQuantity()+quantity);
                    cartItemRepo.findById(cartItem.getId()).get().setQuantity(cartItem.getQuantity());
                    return new ResponseEntity<>("Item added to the cart", HttpStatus.OK);
                }
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product); // Set the Product entity instead of productId
        cartItem.setQuantity(quantity);
        cartItemRepo.save(cartItem);
        if(cart.getItems() == null){
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(cartItem);
        cartRepo.save(cart);
        return new ResponseEntity<>("Item added to the cart", HttpStatus.OK);
    }

    @Override
    @Transactional
    public void updateCartItemQuantity(int cartItemId, int quantity) {
        Optional<CartItem> cartItemOpt = cartItemRepo.findById(cartItemId);
        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            cartItem.setQuantity(quantity);
            cartItemRepo.save(cartItem);
        }
    }

    @Override
    @Transactional
    public void removeItemFromCart(int cartItemId) {
        Optional<CartItem> cartItemOpt = cartItemRepo.findById(cartItemId);
        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            Cart cart = cartItem.getCart();
            cart.getItems().remove(cartItem);
            cartItemRepo.delete(cartItem);
        }

    }
}
