package main;

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
			if (date == null || date.isEmpty() || date.equalsIgnoreCase("null"))
				return false;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false); // 엄격한 날짜 검증
			sdf.parse(date.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    public static void main(String[] args) {
    



	}
}
