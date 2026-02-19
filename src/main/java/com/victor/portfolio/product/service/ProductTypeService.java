package com.victor.portfolio.product.service;

import com.victor.portfolio.product.dto.ProductClassificationDto;
import com.victor.portfolio.product.dto.ProductTypeDto;

import java.util.List;

public interface ProductTypeService {
    List<ProductClassificationDto> listClassifications();
    List<ProductTypeDto> listProductTypes();
    void saveProductType(ProductTypeDto dto);
    void deleteProductType(int idProductType);
}
