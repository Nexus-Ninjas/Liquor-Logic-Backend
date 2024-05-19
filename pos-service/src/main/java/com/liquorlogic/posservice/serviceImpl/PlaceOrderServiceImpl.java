package com.liquorlogic.posservice.service;

import com.liquorlogic.posservice.entity.OrderItem;
import com.liquorlogic.posservice.entity.PlaceOrder;
import com.liquorlogic.posservice.repository.PlaceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {
    @Autowired
    private PlaceOrderRepository placeOrderRepository;

    public List<PlaceOrder> getAllOrders() {
        return placeOrderRepository.findAll();
    }

    public Optional<PlaceOrder> getOrderById(UUID id) {
        return placeOrderRepository.findById(id);
    }

    public PlaceOrder saveOrder(PlaceOrder order) {
        for (OrderItem item : order.getItems()) {
            item.setPlaceOrder(order);
        }
        return placeOrderRepository.save(order);
    }

    public PlaceOrder updateOrder(UUID id, PlaceOrder orderDetails) {
        PlaceOrder order = placeOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setItems(orderDetails.getItems());
        order.setCreateDate(orderDetails.getCreateDate());
        order.setUpdateDate(orderDetails.getUpdateDate());
        for (OrderItem item : order.getItems()) {
            item.setPlaceOrder(order);
        }
        return placeOrderRepository.save(order);
    }

    public void deleteOrder(UUID id) {
        placeOrderRepository.deleteById(id);
    }
}
