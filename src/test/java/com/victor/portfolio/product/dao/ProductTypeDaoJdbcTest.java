package com.victor.portfolio.product.dao;

import com.victor.portfolio.product.dao.impl.ProductTypeDaoImpl;
import com.victor.portfolio.product.dto.ProductClassificationDto;
import com.victor.portfolio.product.dto.ProductTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(ProductTypeDaoImpl.class)
class ProductTypeDaoJdbcTest {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Test
    void should_list_seeded_classifications_and_product_types() {
        List<ProductClassificationDto> classifications = productTypeDao.listClassifications();
        List<ProductTypeDto> productTypes = productTypeDao.listProductTypes();

        assertThat(classifications).isNotNull();
        assertThat(productTypes).isNotNull();

        // data.sql seeds 3 classifications and 3 product types
        assertThat(classifications).hasSize(3);
        assertThat(productTypes).hasSize(3);

        // quick sanity check (not hard-coupled to ordering)
        assertThat(classifications)
                .extracting(ProductClassificationDto::getCode)
                .contains("CLS-A", "CLS-B", "CLS-C");

        assertThat(productTypes)
                .extracting(ProductTypeDto::getCode)
                .contains("PT-001", "PT-002", "PT-003");
    }

    @Test
    void should_save_and_delete_product_type_via_sp_style_calls() {
        int before = productTypeDao.listProductTypes().size();

        ProductTypeDto newDto = ProductTypeDto.builder()
                // IMPORTANT: your DAO sends 0 when null => INSERT
                .idProductType(null)
                .code("PT-999")
                .name("Product Type 999")
                .idClassification(1)
                .status(1)
                .build();

        int saveFlag = productTypeDao.saveProductType(newDto);
        assertThat(saveFlag).isEqualTo(1);

        List<ProductTypeDto> afterInsert = productTypeDao.listProductTypes();
        assertThat(afterInsert).hasSize(before + 1);

        ProductTypeDto inserted = afterInsert.stream()
                .filter(p -> "PT-999".equals(p.getCode()))
                .findFirst()
                .orElseThrow();

        int deleteFlag = productTypeDao.deleteProductType(inserted.getIdProductType());
        assertThat(deleteFlag).isEqualTo(1);

        List<ProductTypeDto> afterDelete = productTypeDao.listProductTypes();
        assertThat(afterDelete)
                .extracting(ProductTypeDto::getCode)
                .doesNotContain("PT-999");
    }
}