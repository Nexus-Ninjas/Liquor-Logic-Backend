package com.liquorlogic.itemservice.controller;

import com.liquorlogic.itemservice.entity.Item;
import com.liquorlogic.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CypsoLabs
 */
@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final ItemService itemService;
    private static final org.apache.logging.log4j.Logger loggerLog4J = LogManager.getLogger(ItemController.class);

    @PostMapping("/save")
    public ResponseEntity<Item> saveItem(@RequestBody Map<String, String> credentials) {
        loggerLog4J.info("Start saveItem");
        try {
            String[] requiredFileds = {"supplierId","categoryId","subcategoryId","brandId",
                    "SKU","image", "name", "description", "qty", "unitPrice", "manufactureDate", "expireDate","employeeId"};
            validateMap(credentials, requiredFileds);

            Item item = new Item();
            UUID itemId = credentials.get("itemId") != null ? UUID.fromString(credentials.get("itemId")) : null;

            if (itemId != null) {
                Optional<Item> byID = itemService.findByItemId(itemId);
                if (byID.isPresent()) {
                    item.setItemId(itemId);

                }
            }
            item.setSupplierId(UUID.fromString(credentials.get("supplierId")));
            item.setCategoryId(UUID.fromString(credentials.get("categoryId")));
            item.setSubcategoryId(UUID.fromString(credentials.get("subcategoryId")));
            item.setBrandId(UUID.fromString(credentials.get("brandId")));
            item.setSKU(credentials.get("SKU"));
            item.setImage(credentials.get("image"));
            item.setName(credentials.get("name"));
            item.setDescription(credentials.get("description"));
            item.setQty(Integer.parseInt(credentials.get("qty")));
            item.setUnitPrice(Double.parseDouble(credentials.get("unitPrice")));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date manufactureDate = dateFormat.parse(credentials.get("manufactureDate"));
            Date expireDate = dateFormat.parse(credentials.get("expireDate"));
            item.setManufactureDate(manufactureDate);
            item.setExpireDate(expireDate);
            item.setEmployeeId(UUID.fromString(credentials.get("employeeId")));


            Date currentDate = new Date();
            item.setUpdateDate(currentDate);
            if (itemId ==null) {
                item.setCreateDate(currentDate);
            }
            return ResponseEntity.ok(itemService.saveItem(item));
        } catch (Exception e) {
            handleException(e);
            loggerLog4J.error("Error Occurred while saving Item");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }finally {
            loggerLog4J.info("End saveItem");
        }

    }


    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        loggerLog4J.info("Start getAllItem");
        try {
            loggerLog4J.info("End getAllItem");
            return ResponseEntity.ok(itemService.getAllItem());
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteItem(@RequestParam UUID itemId) {
        loggerLog4J.info("Start deleteItem");

        // Find the item by itemId
        Optional<Item> optionalItem = itemService.findByItemId(itemId);

        if (optionalItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }

        try {
            Item item = optionalItem.get();
            itemService.deleteItem(item);
            loggerLog4J.info("End deleteItem");
            return ResponseEntity.ok("Item Deleted Successfully");

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }


    }


    @PostMapping ("/itemId")
    public ResponseEntity <Optional<Item>> findByItemId (@RequestParam UUID itemId){
        loggerLog4J.info("Start findByItemId ");
        try {
            loggerLog4J.info("End findByItemId ");
            Optional<Item> item = itemService.findByItemId(itemId);
            if (item.isPresent()) {
                return ResponseEntity.ok(Optional.of(item.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PostMapping("/supplierId")
    public ResponseEntity<Item> findBySupplierId (@RequestParam UUID supplierId){
        loggerLog4J.info("Start findBySupplierId");
        try {
            loggerLog4J.info("End findBySupplierId");
            Item item = itemService.findBySupplierId(supplierId);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/brandId")
    public ResponseEntity <Item> findByBrandId (@RequestParam UUID brandId){
        loggerLog4J.info("Start findByBrandId");
        try{
            loggerLog4J.info("End findByBrandId");
            Item item = itemService.findByBrandId(brandId);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/categoryId")
    public ResponseEntity <Item> findByCategoryId (@RequestParam UUID categoryId){
        loggerLog4J.info("Start findByCategoryId");
        try{
            loggerLog4J.info("End findByCategoryId");
            Item item = itemService.findByCategoryId(categoryId);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/subcategoryId")
    public ResponseEntity <Item> findBySubcategoryId (@RequestParam UUID subcategoryId){
        loggerLog4J.info("Start findBySubcategoryId");
        try{
            loggerLog4J.info("End findBySubcategoryId");
            Item item = itemService.findBySubcategoryId(subcategoryId);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/SKU")
    public ResponseEntity <Item> findBySKU (@RequestParam String SKU){
        loggerLog4J.info("Start findBySKU");
        try{
            loggerLog4J.info("End findBySKU");
            Item item = itemService.findBySKU(SKU);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/unitPrice")
    public ResponseEntity <Item> findByUnitPrice (@RequestParam Double unitPrice){
        loggerLog4J.info("Start findByUnitPrice");
        try{
            loggerLog4J.info("End findByUnitPrice");
            Item item = itemService.findByUnitPrice(unitPrice);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/name")
    public ResponseEntity <Item> findByName (@RequestParam String name){
        loggerLog4J.info("Start findByName");
        try{
            loggerLog4J.info("End findByName");
            Item item = itemService.findByName(name);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/manufactureDate")
    public ResponseEntity <Item> findByManufactureDate (@RequestBody Map<String, String> requestBody){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        loggerLog4J.info("Start findByManufactureDate");
        try{
            String manufactureDateString = requestBody.get("manufactureDate");

            if (manufactureDateString == null) {
                return ResponseEntity.badRequest().build();
            }  Date manufactureDate = dateFormat.parse(manufactureDateString);

            Item item = itemService.findByManufactureDate(manufactureDate);

            if (item != null) {
                loggerLog4J.info("End findByManufactureDate");
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PostMapping("/expireDate")
    public ResponseEntity <Item> findByExpireDate (@RequestBody Map<String, String> requestBody){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        loggerLog4J.info("Start findByExpireDate");
        try{
            String expireDateString = requestBody.get("expireDate");

            if (expireDateString == null) {
                return ResponseEntity.badRequest().build();
            }  Date expireDate = dateFormat.parse(expireDateString);

            Item item = itemService.findByExpireDate(expireDate);

            if (item != null) {
                loggerLog4J.info("End findByExpireDate");
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.notFound().build(); // Return not found if item is null
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }
    @PostMapping("/employeeId")
    public ResponseEntity<Item> itemCreatedBy (@RequestParam UUID employeeId ){
        loggerLog4J.info("Start findByEmployeeId");
        try {
            loggerLog4J.info("End findByEmployeeId");
            Item item = itemService.itemCreatedBy(employeeId);
            if (item != null) {
                return ResponseEntity.ok(item);
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
