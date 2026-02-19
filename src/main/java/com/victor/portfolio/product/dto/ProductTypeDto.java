package com.victor.portfolio.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDto {
    private Integer idProductType;
    private String code;
    private String name;

    private Integer idClassification;
    private String classificationName;

    private Integer status; // 1=Active, 0=Inactive
}
