package com.victor.portfolio.product.controller;

import com.victor.portfolio.common.GlobalExceptionHandler;
import com.victor.portfolio.product.service.ProductTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductTypeController.class)
@Import(GlobalExceptionHandler.class)
class ProductTypeGlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeService productTypeService;

    @Test
    @DisplayName("Should return standardized 500 ApiResponse when unexpected exception occurs")
    void should_return_standardized_500_api_response_when_unexpected_exception_occurs() throws Exception {

        when(productTypeService.listProductTypes())
                .thenThrow(new RuntimeException("Simulated error"));

        mockMvc.perform(get("/api/product-type"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("Unexpected error. Please try again."));
    }
}