package main;

import controller.ReadController;
import repository.ReadRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CsvToOracle {
    private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // Oracle DB URL
    private static final String DB_USER = "scott"; // DB 사용자 이름
    private static final String DB_PASSWORD = "tiger"; // DB 비밀번호

    private static int parseInteger(String value) {
        try {
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("null")) {
                return 0; // 기본값
            }
            return (int) Double.parseDouble(value.trim()); // 실수 처리
        } catch (NumberFormatException e) {
            System.err.println("숫자 파싱 오류: " + value);
            return 0;
        }
    }

    private static double parseDouble(String value) {
        try {
            return (value == null || value.isEmpty()) ? 0.0 : Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            System.err.println("소수점 숫자 파싱 오류: " + value);
            return 0.0;
        }
    }

    private static boolean isValidDate(String date) {
        try {
            if (date == null || date.isEmpty() || date.equalsIgnoreCase("null")) return false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // 엄격한 날짜 검증
            sdf.parse(date.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
//        String csvFilePath = "D:\\woorifisa\\01.lab\\01.java\\RealEstate\\data.csv";
//
//        // SQL INSERT Query
//        String insertQuery = "INSERT INTO real_estate_data ("
//                + "reception_year, district_code, district_name, legal_dong_code, legal_dong_name, lot_type, "
//                + "lot_type_name, main_lot, sub_lot, building_name, contract_date, property_price, building_area, "
//                + "land_area, floor, right_type, cancellation_date, construction_year, building_purpose, report_type, realtor_district_name"
//                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
//        	     Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        	     PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)) {
//
//        	    connection.setAutoCommit(false); // 트랜잭션 시작
//
//        	    String line;
//        	    int batchSize = 500; // 배치 크기를 줄임
//        	    int count = 0;
//
//        	    // CSV 파일의 첫 번째 줄(헤더) 건너뛰기
//        	    br.readLine();
//
//        	    while ((line = br.readLine()) != null) {
//        	        String[] values = line.split(",");
//        	        for (int i = 0; i < values.length; i++) {
//        	            values[i] = values[i].replace("\"", "").trim(); // " 제거 및 공백 제거
//        	        }
//
//        	        // PreparedStatement 바인딩
//        	        try {
//        	            preparedStatement.setInt(1, parseInteger(values[0])); // 접수연도
//        	            preparedStatement.setInt(2, parseInteger(values[1])); // 자치구코드
//        	            preparedStatement.setString(3, values[2]); // 자치구명
//        	            preparedStatement.setInt(4, parseInteger(values[3])); // 법정동코드
//        	            preparedStatement.setString(5, values[4]); // 법정동명
//        	            preparedStatement.setInt(6, parseInteger(values[5])); // 지번구분
//        	            preparedStatement.setString(7, values[6]); // 지번구분명
//        	            preparedStatement.setInt(8, parseInteger(values[7])); // 본번
//        	            preparedStatement.setInt(9, parseInteger(values[8])); // 부번
//        	            preparedStatement.setString(10, values[9]); // 건물명
//        	            preparedStatement.setString(11, values[10]); // 계약일
//        	            preparedStatement.setInt(12, parseInteger(values[11])); // 물건금액
//        	            preparedStatement.setDouble(13, parseDouble(values[12])); // 건물면적
//        	            preparedStatement.setDouble(14, parseDouble(values[13])); // 토지면적
//        	            preparedStatement.setInt(15, parseInteger(values[14])); // 층
//        	            preparedStatement.setString(16, values[15]); // 권리구분
//
//        	            String cancellationDate = values[16];
//        	            if (isValidDate(cancellationDate)) {
//        	                preparedStatement.setString(17, cancellationDate);
//        	            } else {
//        	                preparedStatement.setNull(17, java.sql.Types.DATE);
//        	                System.err.println("잘못된 취소일 데이터: " + cancellationDate);
//        	            }
//
//        	            int constructionYear = parseInteger(values[17]);
//        	            if (constructionYear <= 0 || constructionYear > 9999) {
//        	                preparedStatement.setNull(18, java.sql.Types.INTEGER);
//        	            } else {
//        	                preparedStatement.setInt(18, constructionYear);
//        	            }
//
//        	            preparedStatement.setString(19, values[18]); // 건물용도
//        	            preparedStatement.setString(20, values[19]); // 신고구분
//        	            preparedStatement.setString(21, values[20]); // 중개사시군구명
//
//        	            preparedStatement.addBatch();
//
//        	            if (++count % batchSize == 0) {
//        	                preparedStatement.executeBatch();
//        	            }
//        	        } catch (SQLException e) {
//        	            System.err.println("오류 발생 데이터: " + String.join(",", values));
//        	            e.printStackTrace();
//        	        }
//        	    }
//
//        	    preparedStatement.executeBatch();
//        	    connection.commit();
//
//        	    System.out.println("데이터 삽입 성공!");
//        	} catch (IOException e) {
//        	    System.err.println("CSV 파일을 읽는 중 오류 발생: " + e.getMessage());
//        	} catch (SQLException e) {
//        	    System.err.println("데이터베이스 작업 중 오류 발생: " + e.getMessage());
//        	}
//    }

		ReadRepository readRepository = new ReadRepository();
		ReadController readController = new ReadController(readRepository);

//		readController.readAllDTO();
//		System.out.println(readController.findByProperty("district_name", "서초구"));
//		System.out.println(readController.findByProperty("eid", "1"));
//		readController.findByPropertyDTO("district_name", "서초구");
		readController.findByPropertyDTO("contract_date", "2024-12-31");



    }
}
