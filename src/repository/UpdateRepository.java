package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public boolean changeValue(Long eid, String col, String property) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("Update real_estate_data set "
					+ col +" = "+"? where eid = ?");
			stmt.setString(1,property);
			stmt.setLong(2,eid);
			int result = stmt.executeUpdate();
			
			if (result == 1) {
				System.out.println("변경완료");
				return true;
			}else {
				System.out.println("이상");
			}
		}finally {
			DBUtil.close(conn,stmt);
		}
		return false;
	}
	
	
	public boolean changeProperty(String col, String property) throws SQLException {
		ReadRepository readRepository = new ReadRepository();
		List<RealDTO> estates = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		estates = readRepository.findByPropertyDTO(col, property);
		EndView.displayAsIndexTable(estates);
		
		System.out.println("변경할 행의 인덱스, 컬럼, 변경할 값 입력 (,구분)");
		String input = scanner.nextLine();
		String[] inputs = input.split(",");
		
		if (inputs.length == 3) {
            try {
                int rowIndex = Integer.parseInt(inputs[0].trim()); // 행 인덱스
                String columnName = inputs[1].trim(); // 컬럼 이름
                String newValue = inputs[2].trim(); // 변경할 값

                changeValue((estates.get(rowIndex-1)).getEid(),columnName,newValue);
                
                
            } catch (NumberFormatException e) {
                System.out.println("행 인덱스는 숫자여야 합니다.");
            }
        } else {
            System.out.println("잘못된 입력 형식입니다. 행 인덱스, 컬럼, 변경할 값을 쉼표로 구분하여 입력하세요.");
        }
		
		return false;
		
	}

}