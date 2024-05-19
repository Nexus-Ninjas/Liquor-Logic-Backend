package com.liquorlogic.posservice.repository;

import com.liquorlogic.posservice.entity.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceOrderRepository extends JpaRepository<PlaceOrder, UUID> {
}
