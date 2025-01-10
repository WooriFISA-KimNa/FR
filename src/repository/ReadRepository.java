package repository;

import domain.Estate;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ReadRepository {

    public List<Estate> findAll() {
        List<Estate> estates = new ArrayList<>();
        String query = "SELECT * FROM RealEstateData";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // DBUtil을 통해 Connection 획득
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Estate estate = new Estate(
                        rs.getLong("eid"),
                        rs.getLong("receptionYear"),
                        rs.getLong("districtCode"),
                        rs.getString("districtName"),
                        rs.getLong("legalDongCode"),
                        rs.getString("legalDongName"),
                        rs.getLong("lotType"),
                        rs.getString("lotTypeName"),
                        rs.getLong("mainLot"),
                        rs.getLong("subLot"),
                        rs.getString("buildingName"),
                        rs.getDate("contractDate").toLocalDate(),
                        rs.getLong("propertyPrice"),
                        rs.getLong("buildingArea"),
                        rs.getLong("landArea"),
                        rs.getLong("floor"),
                        rs.getString("rightType"),
                        rs.getDate("cancellationDate") != null ? rs.getDate("cancellationDate").toLocalDate() : null,
                        rs.getLong("constructionYear"),
                        rs.getString("buildingPurpose"),
                        rs.getString("reportType"),
                        rs.getString("realtorDistrictName")
                );
                estates.add(estate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching estates", e);
        } finally {
            // DBUtil을 사용해 자원 정리
            DBUtil.close(rs, stmt, conn);
        }
        return estates;
    }
}
