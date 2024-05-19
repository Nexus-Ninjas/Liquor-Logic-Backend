package com.liquorlogic.posservice.controller;

import com.liquorlogic.posservice.entity.PlaceOrder;
import com.liquorlogic.posservice.service.PlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/place/orders")
public class PlaceOrderController {
    @Autowired
    private PlaceOrderService placeOrderService;

    @GetMapping
    public List<PlaceOrder> getAllOrders() {
        return placeOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceOrder> getOrderById(@PathVariable UUID id) {
        Optional<PlaceOrder> order = placeOrderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PlaceOrder createOrder(@RequestBody PlaceOrder order) {
        return placeOrderService.saveOrder(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceOrder> updateOrder(@PathVariable UUID id, @RequestBody PlaceOrder orderDetails) {
        return ResponseEntity.ok(placeOrderService.updateOrder(id, orderDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        placeOrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
