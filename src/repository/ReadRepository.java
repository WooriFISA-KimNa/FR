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
                        rs.getLong("reception_year"),
                        rs.getLong("district_code"),
                        rs.getString("district_name"),
                        rs.getLong("legal_dong_code"),
                        rs.getString("legal_dong_name"),
                        rs.getLong("lot_type"),
                        rs.getString("lot_type"),
                        rs.getLong("main_lot"),
                        rs.getLong("sub_lot"),
                        rs.getString("building_name"),
                        rs.getDate("contract_date") != null ? rs.getDate("contract_date").toLocalDate() : null,
                        rs.getLong("property_price"),
                        rs.getLong("building_area"),
                        rs.getLong("land_area"),
                        rs.getLong("floor"),
                        rs.getString("right_type"),
                        rs.getDate("cancellation_date") != null ? rs.getDate("cancellation_date").toLocalDate() : null,
                        rs.getLong("construction_year"),
                        rs.getString("building_purpose"),
                        rs.getString("report_type"),
                        rs.getString("realtor_district_name")
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
