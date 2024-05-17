package com.liquorlogic.brandservice.service;

import com.liquorlogic.brandservice.entity.Brand;

import java.util.List;
import java.util.UUID;

/**
 * @author CypsoLabs
 */
public interface BrandService {
    List<Brand> getAllBrand();
    Brand saveBrand(Brand brand);
    Brand findByBrandId(UUID brandId);
    Brand findByBrandName(String brandName);

    boolean deleteBrand(Brand brandId);
}
