package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RealDTO;
import util.DBUtil;
import view.EndView;

public class UpdateRepository {

	public boolean changeDistrictName(long eid, String districtName) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("Update real_estate_data set district_name = ? where eid = ?");
			stmt.setString(1,districtName);
			stmt.setLong(2,eid);
			int result = stmt.executeUpdate();
			
			if (result == 1) {
				return true;
			}
		}finally {
			DBUtil.close(conn,stmt);
		}
		return false;
		
	}
	
	public boolean changeProperty(String col, String property) throws SQLException {
//		Connection conn = null;
//		PreparedStatement stmt = null;
		
		ReadRepository readRepository = new ReadRepository();
		List<RealDTO> estates = new ArrayList<>();
		
		estates = readRepository.findByPropertyDTO(col, property);
		EndView.displayAsIndexTable(estates);
		
		return false;
		
	}

}