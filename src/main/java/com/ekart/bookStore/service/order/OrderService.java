package com.ekart.bookStore.service.order;

import com.ekart.bookStore.entity.order.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    public ResponseEntity<String> placeOrder(String userId);
    public List<Order> getOrderHistory(String userId);

}
