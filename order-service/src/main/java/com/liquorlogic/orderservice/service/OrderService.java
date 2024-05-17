package com.liquorlogic.orderservice.service;

import com.liquorlogic.orderservice.entity.Order;
import com.liquorlogic.orderservice.enums.Status;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * @author CypsoLabs
 */
public interface OrderService {
    Order saveOrder (Order order);
    List<Order> getAllOrder();
    boolean deleteOrder (Order orderId);
    Optional<Order> findByOrderId (UUID orderId);
    Order findByCustomerId (UUID customerId);
    Order findByItemId (UUID itemId);
    Order findByEmployeeId (UUID employeeId);
    Order findByStatus (Status status);
    Order findByCreateDate (Date createDate);
}
