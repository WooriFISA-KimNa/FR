package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRepository {
	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // Oracle DB URL
	private static final String DB_USER = "scott"; // DB 사용자 이름
	private static final String DB_PASSWORD = "tiger"; // DB 비밀번호
	
	public static boolean updateDistrictName(long eid, String districtName) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			preparedStatement = connection.prepareStatement("Update real_estate_data set 자치구명 = ? where eid = ?");
			preparedStatement.setString(1,districtName);
			preparedStatement.setLong(2,eid);
			int result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				return true;
			}
		}finally {
			try {
				if (connection != null) {
					connection.close();
					connection = null;
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}
	

}
