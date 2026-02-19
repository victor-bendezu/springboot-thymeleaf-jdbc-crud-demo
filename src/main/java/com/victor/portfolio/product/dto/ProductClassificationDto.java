package com.victor.portfolio.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductClassificationDto {
    private Integer idClassification;
    private String code;
    private String name;
}
