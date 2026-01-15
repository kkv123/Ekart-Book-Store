package com.ekart.bookStore.controller.order;

import com.ekart.bookStore.entity.order.Order;
import com.ekart.bookStore.repository.user.UserRepo;
import com.ekart.bookStore.service.order.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kart/orders")
public class OrderController {

    @Autowired
    private OrderServiceImp orderService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add")
    public ResponseEntity<String> placeOrder(@RequestParam String userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestParam("userId") String userId) {
        String userIId= userId;
        List<Order> orders = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(orders);
    }
}
