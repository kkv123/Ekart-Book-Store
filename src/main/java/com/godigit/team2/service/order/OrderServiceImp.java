package com.godigit.team2.service.order;

import com.godigit.team2.entity.cart.Cart;
import com.godigit.team2.entity.cart.CartItem;
import com.godigit.team2.entity.order.Order;
import com.godigit.team2.entity.order.OrderItem;
import com.godigit.team2.entity.order.Status;
import com.godigit.team2.entity.product.Product;
import com.godigit.team2.entity.user.User;
import com.godigit.team2.repository.cart.CartRepo;
import com.godigit.team2.repository.order.OrderItemRepo;
import com.godigit.team2.repository.order.OrderRepo;
import com.godigit.team2.repository.user.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderItemRepo orderItemRepository;
    @Autowired
    private CartRepo cartRepo;


    @Transactional
    public ResponseEntity<String> placeOrder(String userId) {
        User user = userRepo.findByUser_id(userId);
        if(user==null) return new ResponseEntity<>("Invalid User ",HttpStatus.BAD_REQUEST);
         Cart cart= cartRepo.findByUserId(userId);
         int totalAmount=0;
         Order order=new Order();
         order.setStatus(Status.PENDING);
         order.setUserId(userId);
         orderRepository.save(order);
         List<CartItem> cartItemList= cart.getItems();
         List<OrderItem> orderItems = new ArrayList<>();
         for(CartItem cartItem: cartItemList){
             Product product= cartItem.getProduct();
             OrderItem orderItem= new OrderItem();
             orderItem.setQuantity(cartItem.getQuantity());
             orderItem.setAmount(product.getProduct_price()*cartItem.getQuantity());
             orderItem.setProduct(product);
             orderItem.setOrder(order);
             totalAmount+=product.getProduct_price()*cartItem.getQuantity();
             orderItems.add(orderItem);
         }
         for(OrderItem orderItem: orderItems){
             orderItemRepository.save(orderItem);
         }
        order.setAmount(totalAmount);
        order.setOrderItemList(orderItems);
//        orderRepository.save(order);

       return new ResponseEntity<>("placed Order", HttpStatus.OK);
    }

    public List<Order> getOrderHistory(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
