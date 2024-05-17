package com.liquorlogic.brandservice.repository;

import com.liquorlogic.brandservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author CypsoLabs
 */
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    Brand findByBrandName(String brandName);
    Brand findByBrandId(UUID brandId);
}
