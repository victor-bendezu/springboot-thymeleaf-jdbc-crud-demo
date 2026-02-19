package com.victor.portfolio.product.dao;

import com.victor.portfolio.product.dto.ProductClassificationDto;
import com.victor.portfolio.product.dto.ProductTypeDto;

import java.util.List;

public interface ProductTypeDao {
    List<ProductClassificationDto> listClassifications();
    List<ProductTypeDto> listProductTypes();
    int saveProductType(ProductTypeDto dto);
    int deleteProductType(int idProductType);
}
