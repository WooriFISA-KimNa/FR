package controller;

import java.sql.SQLException;
import java.util.List;
import dto.RealDTO;
import repository.DeleteRepository;
import view.EndView;

public class DeleteController {
	
	public static void deleteEstate(String main_lot, String sub_lot) {//본번, 부번 입력받아 삭제

		try {
			DeleteRepository.deleteEstate(main_lot, sub_lot);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("해당 주소로 삭제 오류");
		}
	}
	
	public static void selectEstate(String main_lot, String sub_lot){//본번, 부번 입력받아 검색
	        List<RealDTO> estates = DeleteRepository.selectEstate(main_lot, sub_lot);
	        EndView.displayAsTable(estates);
	    }
	
}