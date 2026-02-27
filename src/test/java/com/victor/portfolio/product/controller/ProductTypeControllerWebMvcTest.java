package com.victor.portfolio.product.controller;

import com.victor.portfolio.common.GlobalExceptionHandler;
import com.victor.portfolio.product.service.ProductTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductTypeController.class)
@Import(GlobalExceptionHandler.class)
class ProductTypeControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeService productTypeService;

    @Test
    void save_shouldReturn400_whenCodeExceedsMaxLength() throws Exception {
        // Arrange: mock the service layer so the controller test is isolated.
        Mockito.doNothing().when(productTypeService).saveProductType(any());

        String invalidPayload = """
        {
          "idProductType": null,
          "code": "fsdfdsfdsfdsfdsfdf",
          "name": "xcvxc",
          "idClassification": 2,
          "status": 1
        }
        """;

        mockMvc.perform(post("/api/product-type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.data.code").exists());

        Mockito.verify(productTypeService, never()).saveProductType(any());
    }

    @Test
    void delete_shouldReturn200_whenOk() throws Exception {
        Mockito.doNothing().when(productTypeService).deleteProductType(1);

        mockMvc.perform(delete("/api/product-type/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists());
    }


}
