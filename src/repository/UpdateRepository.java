package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.RealDTO;
import util.DBUtil;
import view.EndView;

public class UpdateRepository {

	public boolean changeValue(RealDTO estate, String col, String property) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// 값 수정
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("Update real_estate_data set " + col + " = ? where eid = ?");
			stmt.setString(1, property);
			stmt.setLong(2, estate.getEid());
			int result = stmt.executeUpdate();

			// 수정한 값 조회
			stmt = conn.prepareStatement("SELECT * FROM real_estate_data where eid = ?");
			stmt.setLong(1, estate.getEid());
			rs = stmt.executeQuery();

			// 수정 전, 수정 후 출력
			if (result == 1 && rs.next()) {
				RealDTO resultEstate = new RealDTO(rs.getLong("eid"), rs.getString("district_name"),
						rs.getString("legal_dong_name"), rs.getLong("main_lot"), rs.getLong("sub_lot"),
						rs.getString("building_name"),
						rs.getDate("contract_date") != null ? rs.getDate("contract_date").toLocalDate() : null,
						rs.getLong("property_price"), rs.getLong("building_area"), rs.getLong("floor"),
						rs.getDate("cancellation_date") != null ? rs.getDate("cancellation_date").toLocalDate() : null,
						rs.getString("building_purpose"), rs.getString("report_type"));

				EndView.updateDisplay(estate, resultEstate);
				return true;
			}
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
		return false;
	}

	public boolean changeProperty(String col, String property) throws SQLException {
		ReadRepository readRepository = new ReadRepository();

		// 조회할 행의 조건 입력, 조회
		List<RealDTO> estates = readRepository.findByPropertyDTO(col, property);
		EndView.displayAsIndexTable(estates);

		// 변경할 행의 인덱스,컬럼,값 받기
		String[] replacementValues = EndView.inputDisplay();

		try {
			int index = Integer.parseInt(replacementValues[0]) - 1;
			changeValue(estates.get(index), replacementValues[1], replacementValues[2]);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("행 인덱스는 숫자여야 합니다.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("유효하지 않은 인덱스입니다.");
		}
		return false;
	}

}