package controller;

import java.sql.SQLException;

import repository.UpdateRepository;

public class UpdateController {

	private UpdateRepository updateRepository;
	
	public UpdateController(UpdateRepository udaRepository) {
		updateRepository = new UpdateRepository();
	}
	
	public void updateDistrictName(long eid, String district_name) {
		try{
			updateRepository.changeDistrictName(eid, district_name);
			System.out.println("수정 성공");
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("해당하는 "+eid+"값이 존재하지 않습니다.");
		}
	}
	

}
