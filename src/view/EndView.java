package view;

import java.util.List;

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

	public static void display(RealDTO realDTO) {
		StringBuilder sb = new StringBuilder();

		// 테이블 헤더
		sb.append(String.format("%-10s %-10s %-10s %-10s %-20s %-12s %-12s %-10s %-10s %-15s %-10s %-10s\n", 
				"구", "동", "본번", "부번", "건물명", "계약일", "금액(원)", "면적(㎡)", "층", "취소일", "용도", "신고구분"));
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
						realDTO.getCancellationDate() == null ? " " : realDTO.getCancellationDate(), realDTO.getBuildingPurpose(),
								realDTO.getReportType()));

		// 출력
		System.out.println(sb.toString());
	}

	// 예외 상황 출력
	public static void showError(String message) {
		System.out.println(message);
	}
}
