package view;

import controller.CreateController;
import controller.ReadController;
import repository.ReadRepository;

public class RunningStartView {

	public static void main(String[] args) {
/*
		System.out.println("***** DB 테이블 생성 *****");
		CreateController.createTable();

		System.out.println("***** sequence 생성 *****");
		CreateController.createSequence();

		System.out.println("***** trigger 생성 *****");
		CreateController.createTrigger();

		System.out.println("***** 테이블에 데이터 생성 *****");
		CreateController.insertData();
*/
		ReadRepository readRepository = new ReadRepository();
		ReadController readController = new ReadController(readRepository);

		readController.findByAnonymousPropertyDTO("building_name", "태릉우성아파트");
		readController.findByPropertyDTO("building_name", "태릉우성아파트");

	}

}
