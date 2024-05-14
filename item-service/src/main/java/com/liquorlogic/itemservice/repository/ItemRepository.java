package com.liquorlogic.itemservice.repository;

import com.liquorlogic.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;
/**
 * @author CypsoLabs
 */
public interface ItemRepository extends JpaRepository<Item, UUID> {
    Item findBySKU(String sku);

    Item findByUnitPrice(Double unitPrice);

    Item findByName(String name);


    Item findBySupplierId(UUID supplierId);

    Item findByBrandId(UUID brandId);

    Item findByCategoryId(UUID categoryId);

    Item findBySubcategoryId(UUID subcategoryId);

    Item findByManufactureDate(Date manufactureDate);

    Item findByExpireDate(Date expireDate);

    Item findByEmployeeId(UUID employeeId);
}
