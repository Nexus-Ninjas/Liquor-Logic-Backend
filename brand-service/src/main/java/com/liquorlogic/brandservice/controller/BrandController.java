package com.liquorlogic.brandservice.controller;

import com.liquorlogic.brandservice.entity.Brand;
import com.liquorlogic.brandservice.service.BrandService;
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
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
    private static final org.apache.logging.log4j.Logger loggerLog4J = LogManager.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;


    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrand() {
        loggerLog4J.info("start getAllBrand");
        try {
            loggerLog4J.info("End getAllBrand");
            return ResponseEntity.ok(brandService.getAllBrand());
        }catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }




    @PostMapping
    public ResponseEntity<Brand> saveBrand(@RequestBody Map<String,String> credentials) {
        loggerLog4J.info("start saveBrand");
        try {
            String[] requiredFields ={"brandName","diliveryCompanyId","discription","createBy","updateBy"};
            validatemap(credentials,requiredFields);

            Brand brand= new Brand();
            UUID brandId = credentials.get("brandId") !=null ? UUID.fromString(credentials.get("brandId")) :null;
            if (brandId != null){
                Optional<Brand> byID = Optional.ofNullable(brandService.findByBrandId(brandId));
                if (byID.isPresent()){
                    brand.setBrandId(brandId);
                }

            }
            //brand.setBrandId(UUID.fromString(credentials.get("brandId")));
            brand.setBrandName(credentials.get("brandName"));
            brand.setDiliveryCompanyId(UUID.fromString(credentials.get("diliveryCompanyId")));
            brand.setDiscription(credentials.get("discription"));
            brand.setCreateBy(credentials.get("createBy"));
            brand.setUpdateBy(credentials.get("updateBy"));


            Date currentDate = new Date();
            brand.setUpdateDate(currentDate);
            if (brandId== null){
                brand.setCreateDate(currentDate);
            }
            return ResponseEntity.ok(brandService.saveBrand(brand));

        } catch (Exception e) {
            handleException(e);
            loggerLog4J.error("Errror  occurred  while saving barand",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }finally {
            loggerLog4J.info("end saveBrand");
        }
    }

    private void validatemap(Map<String, String> credentials, String[] requiredFields) {
        for (String field: requiredFields){
            if (!credentials.containsKey(field) || credentials.get(field) == null || credentials.get(field).isEmpty()) {
                throw new IllegalArgumentException("Missing or empty value for required field: " + field);
            }
        }
    }



    @DeleteMapping
    public ResponseEntity<String> deleteBrand(@RequestParam("brandId") UUID brandId) {
        loggerLog4J.info("Start deleteBrand");
        try {
            Brand brandToDelete = brandService.findByBrandId(brandId);
            if (brandToDelete == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
            }
            brandService.deleteBrand(brandToDelete);
            loggerLog4J.info("End deleteBrand");
            return ResponseEntity.ok("Brand Delete Success..!");
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PostMapping("/brandId")
    public ResponseEntity<Brand> getBrandBybrandId(@RequestParam UUID brandId){
        loggerLog4J.info("Start getBrandByUserId");
        try {
            loggerLog4J.info("End getBrandByUserId");
            Brand brand = brandService.findByBrandId(brandId);
            if (brand != null) {

                return ResponseEntity.ok(brand);
            } else {
                return ResponseEntity.notFound().build(); // Return 404 if brand not found
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/name")
    public ResponseEntity<List<Brand>> getBrandByName(@RequestParam String name){
        loggerLog4J.info("Start getBrandByName");
        try {
            loggerLog4J.info("End getBrandByName");
            return ResponseEntity.ok(Collections.singletonList(brandService.findByBrandName(name)));
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private void handleException(Exception e) {
        loggerLog4J.error("Error ", e);
        e.printStackTrace();
    }
}
