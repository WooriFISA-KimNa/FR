package controller;

import java.sql.SQLException;

import dto.RealDTO;
import repository.CreateRepository;
import view.EndView;


public class CreateController {
	// 테이블 생성
	public static boolean createTable() {
		boolean result = false;

		try {
			result = CreateRepository.createTable();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("테이블 생성 에러 발생");
			EndView.showError("테이블 생성 에러 발생");
		}
		return result;
	}
	
	// 데이터 입력
	public static boolean insertData() {
		boolean result = false;
		
		try {
			result = CreateRepository.insertData();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("데이텁 입력 에러 발생");
			EndView.showError("데이텁 입력 에러 발생");
		}
		return result;
	}

	public static boolean createSequence() {
		boolean result = false;
		
		try {
			result = CreateRepository.createSequence();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("sequence 생성 에러 발생");
			EndView.showError("sequence 생성 에러 발생");
		}
		return result;
	}
	
	public static boolean createTrigger() {
		boolean result = false;
		
		try {
			result = CreateRepository.createTrigger();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("trigger 생성 에러 발생");
			EndView.showError("trigger 생성 에러 발생");
		}
		return result;
	}

	public static boolean insertSingleData(RealDTO property) {
		boolean result = false;

		try{
			result = CreateRepository.insertSingleData(property);
		}catch(SQLException e){
			e.printStackTrace();
			EndView.showError("입력중 오류 발생");
		}

		return result;
	}
}
