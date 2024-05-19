package com.liquorlogic.inventoryservice.serviceImpl;

import com.liquorlogic.inventoryservice.entity.Supplier;
import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import com.liquorlogic.inventoryservice.repository.SupplierRepository;
import com.liquorlogic.inventoryservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private  final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    @Override
    public Optional<Supplier> findAllByStatus(SupplierStatus status) {
        return supplierRepository.findByStatus(status);
    }

    @Override
    public Optional<Supplier> findSupplierById(UUID supplierId) {
        return  supplierRepository.findById(supplierId);
    }

    @Override
    public Optional<Supplier> findByContact(String contact) {
        return supplierRepository.findByContact(contact);
    }

    @Override
    public Optional<Supplier> findBySupplierId(UUID supplierId) {
        return Optional.empty();
    }
}
