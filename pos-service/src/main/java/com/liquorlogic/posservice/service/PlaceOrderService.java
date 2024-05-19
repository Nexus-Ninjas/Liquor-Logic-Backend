package com.liquorlogic.posservice.service;

import com.liquorlogic.posservice.entity.PlaceOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public interface PlaceOrderService {
    public List<PlaceOrder> getAllOrders();

    public Optional<PlaceOrder> getOrderById(UUID id) ;

    public PlaceOrder saveOrder(PlaceOrder order) ;

    public PlaceOrder updateOrder(UUID id, PlaceOrder orderDetails) ;

    public void deleteOrder(UUID id) ;
}
