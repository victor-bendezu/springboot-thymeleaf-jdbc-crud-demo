package com.victor.portfolio.db;

import java.sql.*;

public class H2Procedures {

    // LIST CLASSIFICATIONS
    public static ResultSet spListProductClassifications(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT ID_CLASSIFICATION, CODE, NAME " +
                "FROM PRODUCT_CLASSIFICATION " +
                "WHERE ACTIVE = 1 " +
                "ORDER BY NAME"
        );
        return ps.executeQuery();
    }

    // LIST PRODUCT TYPES
    public static ResultSet spListProductTypes(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT PT.ID_PRODUCT_TYPE, PT.CODE, PT.NAME, PT.STATUS, " +
                "       PC.ID_CLASSIFICATION, PC.NAME AS CLASSIFICATION_NAME " +
                "FROM PRODUCT_TYPE PT " +
                "JOIN PRODUCT_CLASSIFICATION PC ON PC.ID_CLASSIFICATION = PT.ID_CLASSIFICATION " +
                "ORDER BY PT.ID_PRODUCT_TYPE DESC"
        );
        return ps.executeQuery();
    }

    // SAVE (INSERT/UPDATE) - returns flag int
    public static Integer spSaveProductType(Connection conn,
                                           Integer pIdProductType,
                                           String pCode,
                                           String pName,
                                           Integer pIdClassification,
                                           Integer pStatus) throws SQLException {

        if (pCode == null || pCode.trim().isEmpty()) return 0;
        if (pName == null || pName.trim().isEmpty()) return 0;
        if (pIdClassification == null) return 0;
        if (pStatus == null) return 0;

        if (pIdProductType == null || pIdProductType == 0) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO PRODUCT_TYPE (CODE, NAME, ID_CLASSIFICATION, STATUS) VALUES (?, ?, ?, ?)")) {
                ps.setString(1, pCode.trim());
                ps.setString(2, pName.trim());
                ps.setInt(3, pIdClassification);
                ps.setInt(4, pStatus);
                ps.executeUpdate();
            }
        } else {
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE PRODUCT_TYPE SET CODE=?, NAME=?, ID_CLASSIFICATION=?, STATUS=? WHERE ID_PRODUCT_TYPE=?")) {
                ps.setString(1, pCode.trim());
                ps.setString(2, pName.trim());
                ps.setInt(3, pIdClassification);
                ps.setInt(4, pStatus);
                ps.setInt(5, pIdProductType);
                int updated = ps.executeUpdate();
                if (updated == 0) return 0;
            }
        }
        return 1;
    }

    // DELETE - returns flag int
    public static Integer spDeleteProductType(Connection conn, Integer pIdProductType) throws SQLException {
        if (pIdProductType == null || pIdProductType <= 0) return 0;

        try (PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM PRODUCT_TYPE WHERE ID_PRODUCT_TYPE=?")) {
            ps.setInt(1, pIdProductType);
            int deleted = ps.executeUpdate();
            return deleted > 0 ? 1 : 0;
        }
    }
}
