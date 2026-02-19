package com.victor.portfolio.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Code is required.")
    @Size(max = 10, message = "Code must not exceed 10 characters.")
    private String code;

    @NotBlank(message = "Name is required.")
    @Size(max = 120, message = "Name must not exceed 120 characters.")
    private String name;

    @NotNull(message = "Classification is required.")
    private Integer idClassification;

    // En tu RowMapper aparece classificationName, así que debe existir en el DTO
    private String classificationName;

    @NotNull(message = "Status is required.")
    private Integer status;
}
