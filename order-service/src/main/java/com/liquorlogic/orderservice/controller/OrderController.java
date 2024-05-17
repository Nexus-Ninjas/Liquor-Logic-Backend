package com.liquorlogic.orderservice.controller;

import com.liquorlogic.orderservice.entity.Order;
import com.liquorlogic.orderservice.enums.Status;
import com.liquorlogic.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/**
 * @author CypsoLabs
 */
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;
    private static final org.apache.logging.log4j.Logger loggerLog4J = LogManager.getLogger(OrderController.class);


    @PostMapping("/save")
    public ResponseEntity<Order> saveOrder(@RequestBody Map<String, String> credentials) {
        loggerLog4J.info("Start saveOrder");
        try {
            String[] requiredFileds = {"customerId","itemId","employeeId","qty",
                    "createDate","updateDate", "totalAmount", "shippingAddress", "status"};
            validateMap(credentials, requiredFileds);

            Order order = new Order();
            UUID orderId = credentials.get("orderId") != null ? UUID.fromString(credentials.get("orderId")) : null;

            if (orderId != null) {
                Optional<Order> byID = orderService.findByOrderId(orderId);
                if (byID.isPresent()) {
                    order.setOrderId(orderId);

                }
            }
            order.setCustomerId(UUID.fromString(credentials.get("customerId")));
            order.setItemId(UUID.fromString(credentials.get("itemId")));
            order.setEmployeeId(UUID.fromString(credentials.get("employeeId")));
            order.setQty(Integer.parseInt(credentials.get("qty")));
            order.setTotalAmount(Double.parseDouble(credentials.get("totalAmount")));
            order.setShippingAddress(credentials.get("shippingAddress"));
            order.setStatus(Status.valueOf(credentials.get("status")));



            Date currentDate = new Date();
            order.setUpdateDate(currentDate);
            if (orderId ==null) {
                order.setCreateDate(currentDate);
            }
            return ResponseEntity.ok(orderService.saveOrder(order));
        } catch (Exception e) {
            handleException(e);
            loggerLog4J.error("Error Occurred while saving Order");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }finally {
            loggerLog4J.info("End saveOrder");
        }

    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {
        loggerLog4J.info("Start getAllOrder");
        try {
            loggerLog4J.info("End getAllOrder");
            return ResponseEntity.ok(orderService.getAllOrder());
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteOrder(@RequestParam UUID orderId) {
        loggerLog4J.info("Start deleteItem");

        // Find the order by orderId
        Optional<Order> optionalOrder = orderService.findByOrderId(orderId);

        if (optionalOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order not found");
        }

        try {
            Order order = optionalOrder.get();
            orderService.deleteOrder(order);
            loggerLog4J.info("End deleteOrder");
            return ResponseEntity.ok("Order Deleted Successfully");

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }


    }
    @PostMapping ("/orderId")
    public ResponseEntity <Optional<Order>> findByOrderId (@RequestParam UUID orderId){
        loggerLog4J.info("Start findByOrderId ");
        try {
            loggerLog4J.info("End findByOrderId ");
            Optional<Order> order = orderService.findByOrderId(orderId);
            if (order.isPresent()) {
                return ResponseEntity.ok(Optional.of(order.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @PostMapping("/customerId")
    public ResponseEntity<Order> findByCustomerId (@RequestParam UUID customerId){
        loggerLog4J.info("Start findByCustomerId");
        try {
            loggerLog4J.info("End findByCustomerId");
            Order order = orderService.findByCustomerId(customerId);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }
    @PostMapping("/itemId")
    public ResponseEntity<Order> findByItemId (@RequestParam UUID itemId){
        loggerLog4J.info("Start findByItemId");
        try {
            loggerLog4J.info("End findByItemId");
            Order order = orderService.findByItemId(itemId);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @PostMapping("/employeeId")
    public ResponseEntity<Order> findByEmployeeId (@RequestParam UUID employeeId){
        loggerLog4J.info("Start findByEmployeeId");
        try {
            loggerLog4J.info("End findByEmployeeId");
            Order order = orderService.findByEmployeeId(employeeId);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @PostMapping("/status")
    public ResponseEntity<Order> findByStatus (@RequestParam Status status){
        loggerLog4J.info("Start findByStatus");
        try {
            loggerLog4J.info("End findByStatus");
            Order order = orderService.findByStatus(status);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @PostMapping("/createDate")
    public ResponseEntity<Order> findByCreateDate (@RequestParam Date createDate){
        loggerLog4J.info("Start findByCreateDate");
        try {
            loggerLog4J.info("End findByCreateDate");
            Order order = orderService.findByCreateDate(createDate);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    private void handleException(Exception e) {
        loggerLog4J.error("Error ", e);
        e.printStackTrace();
    }


    private void validateMap(Map<String, String> assetCategoryMap, String[] requiredFields) {
        for (String field : requiredFields) {
            if (assetCategoryMap.get(field) == null || assetCategoryMap.get(field).isEmpty()) {
                throw new IllegalArgumentException("Not found " + field);
            }
        }
    }

}
