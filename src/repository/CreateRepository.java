package repository;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.CSVReader;

import dto.RealDTO;
import util.CSVUtil;
import util.DBUtil;

public class CreateRepository {
	public static boolean createTable() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String createTableQuery = """
				CREATE TABLE real_estate_data (
				    eid NUMBER PRIMARY KEY,
				    reception_year NUMBER,
				    district_code NUMBER,
				    district_name VARCHAR2(100),
				    legal_dong_code NUMBER,
				    legal_dong_name VARCHAR2(100),
				    lot_type NUMBER,
				    lot_type_name VARCHAR2(50),
				    main_lot NUMBER,
				    sub_lot NUMBER,
				    building_name VARCHAR2(200),
				    contract_date DATE,
				    property_price NUMBER,
				    building_area NUMBER,
				    land_area NUMBER,
				    floor NUMBER,
				    right_type VARCHAR2(50),
				    cancellation_date DATE,
				    construction_year NUMBER(4),
				    building_purpose VARCHAR2(100),
				    report_type VARCHAR2(50),
				    realtor_district_name VARCHAR2(100)
				)
				""";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM USER_TABLES WHERE TABLE_NAME = 'REAL_ESTATE_DATA'");

			ResultSet rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt(1) == 0) {
				pstmt = conn.prepareStatement(createTableQuery);
				result = pstmt.executeUpdate();
				System.out.println("Table created successfully.");
			} else {
				System.out.println("Table already exists.");
				pstmt = conn.prepareStatement("DROP TABLE real_estate_data");
				result = pstmt.executeUpdate();

				pstmt = conn.prepareStatement(createTableQuery);
				result = pstmt.executeUpdate();

				System.out.println("Table created successfully.");
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	public static boolean insertData() throws SQLException {
		String insertQuery = """
				INSERT INTO REAL_ESTATE_DATA (
				    reception_year, district_code, district_name, legal_dong_code, legal_dong_name, lot_type,
				    lot_type_name, main_lot, sub_lot, building_name, contract_date, property_price, building_area,
				    land_area, floor, right_type, cancellation_date, construction_year, building_purpose, report_type, realtor_district_name
				)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)
				""";
		CSVReader reader = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// CSVReader, Connection, PreparedStatement 생성
			reader = new CSVReader(new FileReader("data.csv"));
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD'");

			// NLS_DATE_FORMAT 설정
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(insertQuery);

			conn.setAutoCommit(false); // 트랜잭션 시작

			String[] values;
			int successCount = 0;
			int failureCount = 0;
			int batchSize = 5000; // 배치 크기 설정
			int count = 0;

			// CSV 파일의 첫 번째 줄(헤더) 건너뛰기
			reader.readNext(); // 헤더 건너뛰기

			while ((values = reader.readNext()) != null) {
				try {
					// PreparedStatement에 데이터 바인딩
					pstmt.setInt(1, CSVUtil.parseInteger(values[0])); // 접수연도
					pstmt.setInt(2, CSVUtil.parseInteger(values[1])); // 자치구코드
					pstmt.setString(3, values[2]); // 자치구명
					pstmt.setInt(4, CSVUtil.parseInteger(values[3])); // 법정동코드
					pstmt.setString(5, values[4]); // 법정동명
					pstmt.setInt(6, CSVUtil.parseInteger(values[5])); // 지번구분
					pstmt.setString(7, values[6]); // 지번구분명
					pstmt.setInt(8, CSVUtil.parseInteger(values[7])); // 본번
					pstmt.setInt(9, CSVUtil.parseInteger(values[8])); // 부번
					pstmt.setString(10, values[9]); // 건물명
					pstmt.setDate(11, CSVUtil.convertToSqlDate(values[10])); // 계약일
					pstmt.setInt(12, CSVUtil.parseInteger(values[11])); // 물건금액
					pstmt.setDouble(13, CSVUtil.parseDouble(values[12])); // 건물면적
					pstmt.setDouble(14, CSVUtil.parseDouble(values[13])); // 토지면적
					pstmt.setInt(15, CSVUtil.parseInteger(values[14])); // 층
					pstmt.setString(16, values[15]); // 권리구분
					pstmt.setDate(17, CSVUtil.convertToSqlDate(values[16])); // 취소일

					int constructionYear = CSVUtil.parseInteger(values[17]);
					if (constructionYear <= 0 || constructionYear > 9999) {
						pstmt.setNull(18, java.sql.Types.INTEGER);
					} else {
						pstmt.setInt(18, constructionYear);
					}

					pstmt.setString(19, values[18]); // 건물용도
					pstmt.setString(20, values[19]); // 신고구분
					pstmt.setString(21, values[20]); // 중개사시군구명

					// 배치에 추가
					pstmt.addBatch();

					if (++count % batchSize == 0) {
						// 배치 실행
						pstmt.executeBatch();
					}

					successCount++;
				} catch (SQLException e) {
					System.err.println("오류 발생 데이터: " + String.join(",", values));
					e.printStackTrace();
					failureCount++;
				}
			}

			// 남아있는 배치 처리
			pstmt.executeBatch();

			conn.commit();
			System.out.printf("데이터 삽입 성공: %d 건, 실패: %d 건%n", successCount, failureCount);
			System.out.println("배치크기: 5000");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("CSV 처리 중 오류 발생: " + e.getMessage());
			return false;
		}
		return true;
	}

	public static boolean createSequence() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String checkSequenceQuery = """
				SELECT COUNT(*)
				FROM user_sequences
				WHERE sequence_name = 'EXAMPLE_SEQ'
				""";

		String checkTriggerQuery = """
				SELECT COUNT(*)
				FROM user_triggers
				WHERE trigger_name = 'EXAMPLE_TRIGGER'
				""";

		String createSequenceQuery = """
				CREATE SEQUENCE example_seq
					START WITH 1
					INCREMENT BY 1
					NOCACHE
				""";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(checkTriggerQuery);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				System.out.println("Trigger already exists.");
				pstmt = conn.prepareStatement("DROP TRIGGER example_trigger");
				result = pstmt.executeUpdate();
				System.out.println("Trigger drop successfully.");
			}

			pstmt = conn.prepareStatement(checkSequenceQuery);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				System.out.println("Sequence already exists.");
				pstmt = conn.prepareStatement("DROP SEQUENCE example_seq");
				result = pstmt.executeUpdate();
				System.out.println("Sequence drop successfully.");
			}

			pstmt = conn.prepareStatement(createSequenceQuery);
			result = pstmt.executeUpdate();

			System.out.println("sequence created successfully.");
		} finally {
			DBUtil.close(conn, pstmt);
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	public static boolean createTrigger() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;

		String createTriggerQuery = """
				CREATE OR REPLACE TRIGGER example_trigger
				BEFORE INSERT ON real_estate_data
				FOR EACH ROW
				BEGIN
				    IF :NEW.eid IS NULL THEN
				        :NEW.eid := example_seq.NEXTVAL;
				    END IF;
				END;
				""";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();

			result = stmt.executeUpdate(createTriggerQuery);

			System.out.println("Trigger created successfully.");
		} finally {
			DBUtil.close(conn, stmt);
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	public static boolean insertSingleData(RealDTO property) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = """
				INSERT INTO real_estate_data
						(district_name, legal_dong_name, main_lot, sub_lot, building_name, contract_date,
						property_price, building_area, floor, cancellation_date, building_purpose, report_type)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, property.getDistrictName());
			pstmt.setString(2, property.getLegalDongName());
			pstmt.setLong(3, property.getMainLot());
			pstmt.setLong(4, property.getSubLot());
			pstmt.setString(5, property.getBuildingName());
			pstmt.setDate(6, java.sql.Date.valueOf(property.getContractDate()));
			pstmt.setLong(7, property.getPropertyPrice());
			pstmt.setLong(8, property.getBuildingArea());
			pstmt.setLong(9, property.getFloor());
			pstmt.setDate(10,
					property.getCancellationDate() != null ? java.sql.Date.valueOf(property.getCancellationDate())
							: null);
			pstmt.setString(11, property.getBuildingPurpose());
			pstmt.setString(12, property.getReportType());

			result = pstmt.executeUpdate();
		} finally {
			DBUtil.close(conn, pstmt);
		}
		if (result == 1) {
			return true;
		}
		return false;
	}
}
