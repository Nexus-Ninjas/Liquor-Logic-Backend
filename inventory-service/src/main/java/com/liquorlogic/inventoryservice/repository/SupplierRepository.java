package com.liquorlogic.inventoryservice.repository;

import com.liquorlogic.inventoryservice.entity.Enum.Status;
import com.liquorlogic.inventoryservice.entity.Supplier;
import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    Optional<Supplier> findByStatus(SupplierStatus status);
    Optional<Supplier> findByContact(String contact);
}
