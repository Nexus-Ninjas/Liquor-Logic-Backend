package com.liquorlogic.orderservice.repository;

import com.liquorlogic.orderservice.entity.Order;
import com.liquorlogic.orderservice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

/**
 * @author CypsoLabs
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByCustomerId(UUID customerId);

    Order findByItemId(UUID itemId);

    Order findByEmployeeId(UUID employeeId);

    Order findByStatus(Status status);

    Order findByCreateDate(Date createDate);
}
