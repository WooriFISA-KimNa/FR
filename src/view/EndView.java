package view;

import java.util.List;
import java.util.Scanner;

import dto.RealDTO;

public class EndView {
	public static void displayAsTable(List<RealDTO> realDTOs) {
		StringBuilder sb = new StringBuilder();

		// 테이블 헤더
		sb.append(String.format("%-10s %-10s %-10s %-10s %-20s %-12s %-12s %-10s %-10s %-15s %-10s %-10s\n", "구", "동",
				"본번", "부번", "건물명", "계약일", "금액(원)", "면적(㎡)", "층", "취소일", "용도", "신고구분"));
		sb.append("=".repeat(150)).append("\n");

		// 데이터 출력
		for (RealDTO real : realDTOs) {
			// 건물명이 너무 길면 자르기
			String shortBuildingName = real.getBuildingName() != null && real.getBuildingName().length() > 10
					? real.getBuildingName().substring(0, 10) + "..."
					: real.getBuildingName() == null ? "N/A" : real.getBuildingName();

			sb.append(String.format("%-10s %-10s %-10d %-10d %-20s %-12s %,12d %-10d %-10d %-15s %-10s %-10s\n",
					real.getDistrictName(), real.getLegalDongName(), real.getMainLot(), real.getSubLot(),
					shortBuildingName, real.getContractDate() == null ? "N/A" : real.getContractDate(),
					real.getPropertyPrice(), real.getBuildingArea(), real.getFloor(),
					real.getCancellationDate() == null ? " " : real.getCancellationDate(), real.getBuildingPurpose(),
					real.getReportType()));
		}

		// 출력
		System.out.println(sb.toString());
	}

	public static void displayAsIndexTable(List<RealDTO> realDTOs) {
		StringBuilder sb = new StringBuilder();

		// 테이블 헤더
		sb.append(String.format("%-10s %-10s %-10s %-10s %-10s %-20s %-12s %-12s %-10s %-10s %-15s %-10s %-10s\n", "번호",
				"구", "동", "본번", "부번", "건물명", "계약일", "금액(원)", "면적(㎡)", "층", "취소일", "용도", "신고구분"));
		sb.append("=".repeat(150)).append("\n");

		int index = 1;
		// 데이터 출력
		for (RealDTO real : realDTOs) {
			// 건물명이 너무 길면 자르기
			String shortBuildingName = real.getBuildingName() != null && real.getBuildingName().length() > 10
					? real.getBuildingName().substring(0, 10) + "..."
					: real.getBuildingName() == null ? "N/A" : real.getBuildingName();

			sb.append(String.format("%-10d %-10s %-10s %-10d %-10d %-20s %-12s %,12d %-10d %-10d %-15s %-10s %-10s\n",
					index++, real.getDistrictName(), real.getLegalDongName(), real.getMainLot(), real.getSubLot(),
					shortBuildingName, real.getContractDate() == null ? "N/A" : real.getContractDate(),
					real.getPropertyPrice(), real.getBuildingArea(), real.getFloor(),
					real.getCancellationDate() == null ? " " : real.getCancellationDate(), real.getBuildingPurpose(),
					real.getReportType()));
		}

		// 출력
		System.out.println(sb.toString());
	}

	public static void singleDisplay(RealDTO realDTO) {
		StringBuilder sb = new StringBuilder();

		// 테이블 헤더
		sb.append(String.format("%-10s %-10s %-10s %-10s %-20s %-12s %-12s %-10s %-10s %-15s %-10s %-10s\n", "구", "동",
				"본번", "부번", "건물명", "계약일", "금액(원)", "면적(㎡)", "층", "취소일", "용도", "신고구분"));
		sb.append("=".repeat(150)).append("\n");

		// 데이터 출력
		// 건물명이 너무 길면 자르기
		String shortBuildingName = realDTO.getBuildingName() != null && realDTO.getBuildingName().length() > 10
				? realDTO.getBuildingName().substring(0, 10) + "..."
				: realDTO.getBuildingName() == null ? "N/A" : realDTO.getBuildingName();

		sb.append(String.format("%-10s %-10s %-10d %-10d %-20s %-12s %,12d %-10d %-10d %-15s %-10s %-10s\n",
				realDTO.getDistrictName(), realDTO.getLegalDongName(), realDTO.getMainLot(), realDTO.getSubLot(),
				shortBuildingName, realDTO.getContractDate() == null ? "N/A" : realDTO.getContractDate(),
				realDTO.getPropertyPrice(), realDTO.getBuildingArea(), realDTO.getFloor(),
				realDTO.getCancellationDate() == null ? " " : realDTO.getCancellationDate(),
				realDTO.getBuildingPurpose(), realDTO.getReportType()));

		// 출력
		System.out.println(sb.toString());
	}

	public static String[] inputDisplay() {
		Scanner scanner = new Scanner(System.in);

		String[] prompts = { "변경할 행의 인덱스: ", "변경할 컬럼명: ", "변경할 값: " };
		String[] replacementValues = new String[prompts.length];

		 for (int i = 0; i < prompts.length; i++) {
             System.out.print(prompts[i]);
             replacementValues[i] = scanner.next();
         }

		return replacementValues;
	}
	
	public static void updateDisplay(RealDTO r1, RealDTO r2) {
		System.out.println("수정 전 값");
		EndView.singleDisplay(r1);
		System.out.println("수정 후 값");
		EndView.singleDisplay(r2);
	}

	public static void displayObject(List<List<Object>> results) {
		// 결과 출력
		/*
        for (List<Object> row : results) {
            for (Object value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        */
		//Refactor Using StreamAPI
		results.stream()
	       .forEach(row -> {
	           row.stream()
	               .forEach(value -> System.out.print(value + "\t"));
	           System.out.println();
	       });
		
	}

	// 예외 상황 출력
	public static void showError(String message) {
		System.out.println(message);
	}
	
	
}
