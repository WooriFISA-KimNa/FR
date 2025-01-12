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
            
            // 숫자만 남기고 나머지 문자 제거 (정규식 사용)
            String filteredValue = value.replaceAll("[^0-9]", "").trim();
            
            // 필터링된 값이 비어있으면 0 반환
            if (filteredValue.isEmpty()) {
                return 0;
            }
            
            return Integer.parseInt(filteredValue); // 정수로 파싱
        } catch (NumberFormatException e) {
            System.err.println("숫자 파싱 오류: " + value);
            return 0;
        }
    }

	public static double parseDouble(String value) {
	    try {
	        if (value == null || value.isEmpty()) {
	            return 0.0;
	        }

	        // 숫자와 소수점만 남기고 나머지 문자 제거 (정규식 사용)
	        String filteredValue = value.replaceAll("[^0-9.]", "").trim();
	        
	        // 필터링된 값이 비어있으면 0.0 반환
	        if (filteredValue.isEmpty()) {
	            return 0.0;
	        }
	        
	        return Double.parseDouble(filteredValue); // 소수점 숫자로 파싱
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
}
