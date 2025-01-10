package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CSVUtil {
	public static int parseInteger(String value) {
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

	public static double parseDouble(String value) {
        try {
            return (value == null || value.isEmpty()) ? 0.0 : Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            System.err.println("소수점 숫자 파싱 오류: " + value);
            return 0.0;
        }
    }

	
	public static Date convertToSqlDate(String date) {
	    try {
	        if (date == null || date.isEmpty() || date.equalsIgnoreCase("null")) {
	            return null; // date가 null이거나 빈 문자열일 경우 null 반환
	        }
	        // 날짜가 "yyyyMMdd" 형식이면 "yyyy-MM-dd"로 변환
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyyMMdd");
            sdfInput.setLenient(false);
            java.util.Date parsedDate = sdfInput.parse(date.trim());
            return new Date(parsedDate.getTime()); // java.sql.Date로 변환 후 반환

	    } catch (ParseException e) {
	        // 예외가 발생한 경우 null 반환
	        return null;
	    }
	}
	
	public static String handleEmptyString(String value) {
	    return (value == null || value.trim().isEmpty()) ? null : value.trim();
	}
}
