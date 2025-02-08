package com.godigit.team2.controller.order;

import com.godigit.team2.entity.order.Order;
import com.godigit.team2.repository.user.UserRepo;
import com.godigit.team2.service.order.OrderServiceImp;
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
