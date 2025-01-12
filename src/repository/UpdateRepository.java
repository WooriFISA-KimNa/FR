package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.RealDTO;
import util.DBUtil;
import view.EndView;

public class UpdateRepository {

//	public boolean changeDistrictName(long eid, String districtName) throws SQLException {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = DBUtil.getConnection();
//			stmt = conn.prepareStatement("Update real_estate_data set district_name = ? where eid = ?");
//			stmt.setString(1,districtName);
//			stmt.setLong(2,eid);
//			int result = stmt.executeUpdate();
//			
//			if (result == 1) {
//				return true;
//			}
//		}finally {
//			DBUtil.close(conn,stmt);
//		}
//		return false;
//		
//	}

	public boolean changeValue(RealDTO estate, String col, String property) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("Update real_estate_data set " + col + " = " + "? where eid = ?");
			stmt.setString(1, property);
			stmt.setLong(2, estate.getEid());
			int result = stmt.executeUpdate();

			String selectQuery = "SELECT * FROM real_estate_data where eid = ?";
			stmt = conn.prepareStatement(selectQuery);
			stmt.setLong(1, estate.getEid());
			rs = stmt.executeQuery();

			if (result == 1 && rs.next()) {
				System.out.println("기존값");
//				EndView.display(estate);

				estate = new RealDTO(rs.getLong("eid"), rs.getString("district_name"), rs.getString("legal_dong_name"),
						rs.getLong("main_lot"), rs.getLong("sub_lot"), rs.getString("building_name"),
						rs.getDate("contract_date") != null ? rs.getDate("contract_date").toLocalDate() : null,
						rs.getLong("property_price"), rs.getLong("building_area"), rs.getLong("floor"),
						rs.getDate("cancellation_date") != null ? rs.getDate("cancellation_date").toLocalDate() : null,
						rs.getString("building_purpose"), rs.getString("report_type"));

				System.out.println("수정값");
//				EndView.display(estate);

				return true;
			}
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
		return false;
	}

	public boolean changeProperty(String col, String property) throws SQLException {
		ReadRepository readRepository = new ReadRepository();
		List<RealDTO> estates = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		estates = readRepository.findByPropertyDTO(col, property);
//		EndView.displayAsIndexTable(estates);

		System.out.print("변경할 행의 인덱스: ");
		String index = scanner.next();

		System.out.print("변경할 행의 컬럼명: ");
		String column = scanner.next();

		System.out.print("변경할 값 입력: ");
		String value = scanner.next();

		String input = scanner.nextLine();


		try {
			int rowIndex = Integer.parseInt(index); // 행 인덱스
			String columnName = column; // 컬럼 이름
			String newValue = value; // 변경할 값

			changeValue(estates.get(rowIndex - 1), columnName, newValue);

		} catch (NumberFormatException e) {
			System.out.println("행 인덱스는 숫자여야 합니다.");
		}

		return false;

	}

}