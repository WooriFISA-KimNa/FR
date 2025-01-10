package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBUtil;

public class UpdateRepository {
	
	public static boolean updateDistrictName(long eid, String districtName) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("Update real_estate_data set 자치구명 = ? where eid = ?");
			stmt.setString(1,districtName);
			stmt.setLong(2,eid);
			int result = stmt.executeUpdate();
			
			if (result == 1) {
				return true;
			}
		}finally {
			DBUtil.close(stmt, conn);
		}
		return false;
	}
	
	public static boolean update(long eid, String districtName) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("Update real_estate_data set 자치구명 = ? where eid = ?");
			stmt.setString(1,districtName);
			stmt.setLong(2,eid);
			int result = stmt.executeUpdate();
			
			if (result == 1) {
				return true;
			}
		}finally {
			DBUtil.close(stmt, conn);
		}
		return false;
	}
	

}
