package controller;

import java.sql.SQLException;

import dto.RealDTO;
import repository.CreateRepository;
import view.EndView;


public class CreateController {
	// 테이블 생성
	public static void createTable() {
		try {
			CreateRepository.createTable();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("테이블 생성 에러 발생");
			EndView.showError("테이블 생성 에러 발생");
		}
	}
	
	// 데이터 입력
	public static void insertData() {
		try {
			CreateRepository.insertData();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("데이텁 입력 에러 발생");
			EndView.showError("데이텁 입력 에러 발생");
		}
	}

	public static void createSequence() {
		try {
			CreateRepository.createSequence();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("sequence 생성 에러 발생");
			EndView.showError("sequence 생성 에러 발생");
		}
	}
	
	public static void createTrigger() {
		try {
			CreateRepository.createTrigger();
		} catch (SQLException e) {
			e.printStackTrace();
			//RunningEndView.showError("trigger 생성 에러 발생");
			EndView.showError("trigger 생성 에러 발생");
		}
	}

	public static void insertSingleData(RealDTO property) {
		try{
			CreateRepository.insertSingleData(property);
		}catch(SQLException e){
			e.printStackTrace();
			EndView.showError("입력중 오류 발생");
		}
	}
}
