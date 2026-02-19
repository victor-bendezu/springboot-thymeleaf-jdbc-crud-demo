package com.victor.portfolio.product.dao.impl;

import com.victor.portfolio.product.dao.ProductTypeDao;
import com.victor.portfolio.product.dto.ProductClassificationDto;
import com.victor.portfolio.product.dto.ProductTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductTypeDaoImpl implements ProductTypeDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<ProductClassificationDto> classificationMapper = (rs, rowNum) -> ProductClassificationDto.builder()
            .idClassification(rs.getInt("ID_CLASSIFICATION"))
            .code(rs.getString("CODE"))
            .name(rs.getString("NAME"))
            .build();

    private final RowMapper<ProductTypeDto> productTypeMapper = (rs, rowNum) -> ProductTypeDto.builder()
            .idProductType(rs.getInt("ID_PRODUCT_TYPE"))
            .code(rs.getString("CODE"))
            .name(rs.getString("NAME"))
            .idClassification(rs.getInt("ID_CLASSIFICATION"))
            .classificationName(rs.getString("CLASSIFICATION_NAME"))
            .status(rs.getInt("STATUS"))
            .build();

    @Override
    public List<ProductClassificationDto> listClassifications() {
        return jdbcTemplate.query("CALL SP_LIST_PRODUCT_CLASSIFICATIONS()", classificationMapper);
    }

    @Override
    public List<ProductTypeDto> listProductTypes() {
        return jdbcTemplate.query("CALL SP_LIST_PRODUCT_TYPES()", productTypeMapper);
    }

    @Override
    public int saveProductType(ProductTypeDto dto) {
        Integer flag = jdbcTemplate.queryForObject(
                "CALL SP_SAVE_PRODUCT_TYPE(?, ?, ?, ?, ?)",
                Integer.class,
                dto.getIdProductType() == null ? 0 : dto.getIdProductType(),
                dto.getCode(),
                dto.getName(),
                dto.getIdClassification(),
                dto.getStatus()
        );
        return flag == null ? 0 : flag;
    }

    @Override
    public int deleteProductType(int idProductType) {
        Integer flag = jdbcTemplate.queryForObject(
                "CALL SP_DELETE_PRODUCT_TYPE(?)",
                Integer.class,
                idProductType
        );
        return flag == null ? 0 : flag;
    }
}
