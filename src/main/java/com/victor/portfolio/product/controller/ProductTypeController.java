package com.victor.portfolio.product.controller;

import com.victor.portfolio.common.ApiResponse;
import com.victor.portfolio.product.dto.ProductClassificationDto;
import com.victor.portfolio.product.dto.ProductTypeDto;
import com.victor.portfolio.product.service.ProductTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    // View
    @GetMapping("/product-type")
    public String view() {
        return "product_type";
    }

    // APIs
    @GetMapping("/api/product-type/classifications")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<ProductClassificationDto>>> listClassifications() {
        List<ProductClassificationDto> data = productTypeService.listClassifications();
        return ResponseEntity.ok(ApiResponse.ok("OK", data));
    }

    @GetMapping("/api/product-type")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<ProductTypeDto>>> listProductTypes() {
        List<ProductTypeDto> data = productTypeService.listProductTypes();
        return ResponseEntity.ok(ApiResponse.ok("OK", data));
    }

    @PostMapping("/api/product-type")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> save(@Valid @RequestBody ProductTypeDto dto) {
        productTypeService.saveProductType(dto);
        return ResponseEntity.ok(ApiResponse.ok("Saved successfully.", null));
    }

    @DeleteMapping("/api/product-type/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") Integer id) {
        productTypeService.deleteProductType(id);
        return ResponseEntity.ok(ApiResponse.ok("Deleted successfully.", null));
    }
}
