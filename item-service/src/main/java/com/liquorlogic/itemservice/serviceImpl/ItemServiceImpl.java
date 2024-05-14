package com.liquorlogic.itemservice.serviceImpl;

import com.liquorlogic.itemservice.entity.Item;
import com.liquorlogic.itemservice.repository.ItemRepository;
import com.liquorlogic.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * @author CypsoLabs
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    @Autowired
    private final ItemRepository itemRepository;
    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean deleteItem(Item itemId) {
        itemRepository.delete(itemId);
        return true;

    }

    @Override
    public Optional<Item> findByItemId(UUID itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public Item findBySupplierId(UUID supplierId) {
        return itemRepository.findBySupplierId(supplierId);
    }

    @Override
    public Item findByBrandId(UUID brandId) {
        return itemRepository.findByBrandId(brandId);
    }

    @Override
    public Item findByCategoryId(UUID categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    @Override
    public Item findBySubcategoryId(UUID subcategoryId) {
        return itemRepository.findBySubcategoryId(subcategoryId);
    }

    @Override
    public Item findBySKU(String SKU) {
        return itemRepository.findBySKU(SKU);
    }

    @Override
    public Item findByUnitPrice(Double unitPrice) {
        return itemRepository.findByUnitPrice(unitPrice);
    }

    @Override
    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public Item findByManufactureDate(Date manufactureDate) {
        return itemRepository.findByManufactureDate(manufactureDate);
    }

    @Override
    public Item findByExpireDate(Date expireDate) {
        return itemRepository.findByExpireDate(expireDate);
    }

    @Override
    public Item itemCreatedBy(UUID employeeId) {
        return itemRepository.findByEmployeeId(employeeId);
    }
}
