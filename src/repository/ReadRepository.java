package repository;

import domain.Estate;
import dto.RealDTO;
import oracle.jdbc.proxy.annotation.Pre;
import util.DBUtil;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ReadRepository {


    public static Object parseInput(String col, String input) {
        try {
            // 날짜 컬럼으로 지정된 경우 날짜로 변환 시도
            if (col.equals("contract_date") || col.equals("cancellation_date")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(input, formatter);
            }

            // 숫자 컬럼으로 지정된 경우 숫자로 변환 시도
            if (col.equals("eid") || col.equals("property_price") || col.equals("main_lot") || col.equals("sub_lot") || col.equals("floor")) {
                return Long.parseLong(input);
            }

            // 기본적으로 문자열로 처리
            return input;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for column: " + col);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for column: " + col);
        }
    }


    public List<Estate> findAll() {
        List<Estate> estates = new ArrayList<>();
        String query = "SELECT * FROM real_estate_data";
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

    public List<RealDTO> findAllDTO() {
        List<RealDTO> estates = new ArrayList<>();
        String query = "SELECT eid, district_name, legal_dong_name, main_lot, sub_lot, building_name, " +
                "contract_date, property_price, building_area, floor, cancellation_date, " +
                "building_purpose, report_type FROM real_estate_data";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                RealDTO realDTO = new RealDTO(
                        rs.getLong("eid"),
                        rs.getString("district_name"),
                        rs.getString("legal_dong_name"),
                        rs.getLong("main_lot"),
                        rs.getLong("sub_lot"),
                        rs.getString("building_name"),
                        rs.getDate("contract_date") != null ? rs.getDate("contract_date").toLocalDate() : null,
                        rs.getLong("property_price"),
                        rs.getLong("building_area"),
                        rs.getLong("floor"),
                        rs.getDate("cancellation_date") != null ? rs.getDate("cancellation_date").toLocalDate() : null,
                        rs.getString("building_purpose"),
                        rs.getString("report_type")
                );
                estates.add(realDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching DTOs", e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return estates;
    }


    public List<Estate> findByProperty(String col, String property) {
        List<Estate> estates = new ArrayList<>();
        String query = "SELECT * FROM real_estate_data where " + col + " = '" + property + " and rownum <= 100'";
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

    public List<RealDTO> findByPropertyDTO(String col, String property) {
        List<RealDTO> estates = new ArrayList<>();
        String query = "SELECT * FROM real_estate_data WHERE " + col + " = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Object parsedProperty = parseInput(col, property);

        try {
            // DBUtil을 통해 Connection 획득
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(query);

            if (parsedProperty instanceof String) {
                pstmt.setString(1, (String) parsedProperty);
            } else if (parsedProperty instanceof Long) {
                pstmt.setLong(1, (Long) parsedProperty);
            } else if (parsedProperty instanceof LocalDate) {
                pstmt.setDate(1, java.sql.Date.valueOf((LocalDate) parsedProperty));
            } else {
                throw new IllegalArgumentException("Unsupported property type: " + property.getClass().getSimpleName());
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                RealDTO realDTO = new RealDTO(
                        rs.getLong("eid"),
                        rs.getString("district_name"),
                        rs.getString("legal_dong_name"),
                        rs.getLong("main_lot"),
                        rs.getLong("sub_lot"),
                        rs.getString("building_name"),
                        rs.getDate("contract_date") != null ? rs.getDate("contract_date").toLocalDate() : null,
                        rs.getLong("property_price"),
                        rs.getLong("building_area"),
                        rs.getLong("floor"),
                        rs.getDate("cancellation_date") != null ? rs.getDate("cancellation_date").toLocalDate() : null,
                        rs.getString("building_purpose"),
                        rs.getString("report_type")
                );
                estates.add(realDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching estates", e);
        } finally {
            // DBUtil을 사용해 자원 정리
            DBUtil.close(rs, pstmt, conn);
        }
        return estates;
    }



}
