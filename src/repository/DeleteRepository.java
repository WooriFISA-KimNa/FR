package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dto.RealDTO;
import util.DBUtil;

public class DeleteRepository {
	
	//본번, 부번 받아 해당 행 삭제
	public static boolean deleteEstate(String main_lot, String sub_lot) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from real_estate_data where main_lot=? and sub_lot=?");
			pstmt.setString(1, main_lot);
			pstmt.setString(2, sub_lot);
			
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			 DBUtil.close(pstmt, con);
		}
		return false;
	}
	
	//본번, 부번 받아 해당 행 출력
	public static List<RealDTO> selectEstate(String main_lot, String sub_lot) {
        List<RealDTO> estates = new ArrayList<>();
        String query = "SELECT * FROM real_estate_data WHERE main_lot=? and sub_lot=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // DBUtil을 통해 Connection 획득
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, main_lot);
            pstmt.setString(2, sub_lot);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                RealDTO realDTO = new RealDTO(
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