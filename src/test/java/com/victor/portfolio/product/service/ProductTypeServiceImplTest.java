package com.victor.portfolio.product.service;

import com.victor.portfolio.common.AppConstants;
import com.victor.portfolio.product.dao.ProductTypeDao;
import com.victor.portfolio.product.dto.ProductTypeDto;
import com.victor.portfolio.product.service.impl.ProductTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductTypeServiceImplTest {

    @Mock
    private ProductTypeDao productTypeDao;

    @InjectMocks
    private ProductTypeServiceImpl productTypeService;

    @Test
    void should_save_product_type_when_dao_returns_success_flag() {
        ProductTypeDto dto = ProductTypeDto.builder()
                .idProductType(null)
                .code("PT-999")
                .name("Product Type 999")
                .idClassification(1)
                .status(1)
                .build();

        when(productTypeDao.saveProductType(dto)).thenReturn(AppConstants.SP_SUCCESS);

        assertDoesNotThrow(() -> productTypeService.saveProductType(dto));

        verify(productTypeDao, times(1)).saveProductType(dto);
        verifyNoMoreInteractions(productTypeDao);
    }

    @Test
    void should_throw_exception_when_save_flag_is_not_success() {
        ProductTypeDto dto = ProductTypeDto.builder()
                .idProductType(null)
                .code("PT-999")
                .name("Product Type 999")
                .idClassification(1)
                .status(1)
                .build();

        when(productTypeDao.saveProductType(dto)).thenReturn(0);

        assertThrows(RuntimeException.class, () -> productTypeService.saveProductType(dto));

        verify(productTypeDao, times(1)).saveProductType(dto);
        verifyNoMoreInteractions(productTypeDao);
    }

    @Test
    void should_delete_product_type_when_dao_returns_success_flag() {
        int id = 10;

        when(productTypeDao.deleteProductType(id)).thenReturn(AppConstants.SP_SUCCESS);

        assertDoesNotThrow(() -> productTypeService.deleteProductType(id));

        verify(productTypeDao, times(1)).deleteProductType(id);
        verifyNoMoreInteractions(productTypeDao);
    }

    @Test
    void should_throw_exception_when_delete_flag_is_not_success() {
        int id = 10;

        when(productTypeDao.deleteProductType(id)).thenReturn(0);

        assertThrows(RuntimeException.class, () -> productTypeService.deleteProductType(id));

        verify(productTypeDao, times(1)).deleteProductType(id);
        verifyNoMoreInteractions(productTypeDao);
    }
}