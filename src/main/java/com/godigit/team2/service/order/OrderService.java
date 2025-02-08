package com.godigit.team2.service.order;

import com.godigit.team2.entity.order.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    public ResponseEntity<String> placeOrder(String userId);
    public List<Order> getOrderHistory(String userId);

}
