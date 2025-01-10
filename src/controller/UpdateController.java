package controller;

import java.sql.SQLException;

import repository.UpdateRepository;

public class UpdateController {

	private UpdateRepository updateRepository;
	
	public UpdateController(UpdateRepository udaRepository) {
		updateRepository = new UpdateRepository();
	}
	
	public void update(String col, String property) {
		try{
			updateRepository.changeProperty(col, property);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("해당하는 값이 존재하지 않습니다.");
		}
	}
}
