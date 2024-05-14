package com.liquorlogic.itemservice.service;

import com.liquorlogic.itemservice.entity.Item;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * @author CypsoLabs
 */
public interface ItemService {
    List<Item> getAllItem();
    Item saveItem (Item item);
    boolean deleteItem (Item itemId);
    Optional<Item> findByItemId (UUID itemId);

    Item findBySupplierId(UUID supplierId);

    Item findByBrandId(UUID brandId);

    Item findByCategoryId(UUID categoryId);

    Item findBySubcategoryId(UUID subcategoryId);

    Item findBySKU(String SKU);

    Item findByUnitPrice(Double unitPrice);

    Item findByName(String name);

    Item findByManufactureDate(Date manufactureDate);

    Item findByExpireDate(Date expireDate);

    Item itemCreatedBy (UUID employeeId);
}
