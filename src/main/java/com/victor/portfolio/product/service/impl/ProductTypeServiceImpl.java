package com.victor.portfolio.product.service.impl;

import com.victor.portfolio.common.AppConstants;
import com.victor.portfolio.product.dao.ProductTypeDao;
import com.victor.portfolio.product.dto.ProductClassificationDto;
import com.victor.portfolio.product.dto.ProductTypeDto;
import com.victor.portfolio.product.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeDao productTypeDao;

    @Override
    public List<ProductClassificationDto> listClassifications() {
        return productTypeDao.listClassifications();
    }

    @Override
    public List<ProductTypeDto> listProductTypes() {
        return productTypeDao.listProductTypes();
    }

    @Override
    public void saveProductType(ProductTypeDto dto) {
        int flag = productTypeDao.saveProductType(dto);
        if (flag != AppConstants.SP_SUCCESS) {
            throw new RuntimeException("An error occurred while processing the transaction (DB).");
        }
    }

    @Override
    public void deleteProductType(int idProductType) {
        int flag = productTypeDao.deleteProductType(idProductType);
        if (flag != AppConstants.SP_SUCCESS) {
            throw new RuntimeException("An error occurred while processing the transaction (DB).");
        }
    }
}
