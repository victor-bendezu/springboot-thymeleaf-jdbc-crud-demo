package com.victor.portfolio.product.controller;

import com.victor.portfolio.product.service.ProductTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductTypeController.class)
class ProductTypeViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductTypeService productTypeService;

    @Test
    void should_render_product_type_view() throws Exception {

        // no necesitamos mockear nada porque la vista no depende
        // de retorno directo en este endpoint

        mockMvc.perform(get("/product-type"))
                .andExpect(status().isOk())
                .andExpect(view().name("product_type"));
    }
}