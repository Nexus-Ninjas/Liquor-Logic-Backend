package com.liquorlogic.brandservice.serviceImpl;

import com.liquorlogic.brandservice.entity.Brand;
import com.liquorlogic.brandservice.repository.BrandRepository;
import com.liquorlogic.brandservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author CypsoLabs
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand findByBrandId(UUID brandId) {
        return brandRepository.findByBrandId(brandId);
    }

    @Override
    public Brand findByBrandName(String brandName) {
        return brandRepository.findByBrandName(brandName);
    }

    @Override
    public boolean deleteBrand(Brand brandId) {
        brandRepository.delete(brandId);
        return true;
    }
}
