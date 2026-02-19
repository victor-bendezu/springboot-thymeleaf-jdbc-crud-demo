INSERT INTO PRODUCT_CLASSIFICATION (CODE, NAME, ACTIVE) VALUES
('CLS-A', 'Category A', 1),
('CLS-B', 'Category B', 1),
('CLS-C', 'Category C', 1);

INSERT INTO PRODUCT_TYPE (CODE, NAME, ID_CLASSIFICATION, STATUS) VALUES
('PT-001', 'Product Type One', 1, 1),
('PT-002', 'Product Type Two', 2, 1),
('PT-003', 'Product Type Three', 3, 0);
