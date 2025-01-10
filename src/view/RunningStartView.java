package view;

import java.util.Scanner;

import controller.CreateController;
import controller.ReadController;
import repository.ReadRepository;

public class RunningStartView {

	public static void main(String[] args) {

		ReadRepository readRepository = new ReadRepository();
		ReadController readController = new ReadController(readRepository);

		//readController.findByAnonymousPropertyDTO("building_name", "태릉우성아파트");
		//readController.findByPropertyDTO("building_name", "태릉우성아파트");

		Scanner scanner = new Scanner(System.in);
		String[] validColumns = { "reception_year", "district_code", "district_name", "legal_dong_code",
				"legal_dong_name", "lot_type", "lot_type_name", "main_lot", "sub_lot", "building_name", "contract_date",
				"property_price", "building_area", "land_area", "floor", "right_type", "cancellation_date",
				"construction_year", "building_purpose", "report_type", "realtor_district_name" };
		String col = "";
		int choice; // 사용자가 선택할 메뉴 번호


//		System.out.println("***** DB 테이블 생성 *****");
//		CreateController.createTable();
//
//		System.out.println("***** sequence 생성 *****");
//		CreateController.createSequence();
//
//		System.out.println("***** trigger 생성 *****");
//		CreateController.createTrigger();
//
//		System.out.println("***** 테이블에 데이터 생성 *****");
//		CreateController.insertData();


		System.out.println("""
				F)ffffff R)rrrrr
				F)       R)    rr
				F)fffff  R)  rrr
				F)       R) rr
				F)       R)   rr
				F)       R)    rr
				""");
		while (true)
			try {
				{
				}
				System.out.println("========================= 메뉴 =========================");
				System.out.println("실행할 옵션을 입력해주세요");
				System.out.println("1. 추가  |  2. 조회  |  3. 수정  |  4. 삭제  |  5.종료");
				System.out.print("실행할 옵션을 입력");

				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					// 추가
					System.out.println("========================= 추가 메뉴 =========================");

					break;
				case 2:
					// 검색
					System.out.println("========================= 조회 메뉴 =========================");
					System.out.println("실행할 옵션을 입력해주세요");
					System.out.println("1. 모든 데이터 조회  |  2. 특정 컬럼에 특정 내용 포함한 데이터 조회  |  3. 특정 컬럼에 기준으로 정렬해 데이터 조회");
					System.out.print("실행할 옵션을 입력: ");
					choice = scanner.nextInt();
					switch (choice) {
					case 1:
						readController.readAllDTO();
						break;
					case 2: {
						boolean validInput = false;
						System.out.println("검색할 특정 컬럼을 입력해주세요 (접수연도 검색-> reception_year 입력)");
						System.out.println(
								"접수연도: reception_year | 자치구코드: district_code | 자치구명: district_name | 법정동코드: legal_dong_code | 법정동명: legal_dong_name");
						System.out.println(
								"지번구분: lot_type | 지번구분명: lot_type_name | 본번: main_lot | 부번: sub_lot | 건물명: building_name");
						System.out.println(
								"계약일: contract_date | 물건금액: property_price | 건물면적: building_area | 토지면적: land_area | 층: floor");
						System.out.println(
								"권리구분: right_type | 취소일: cancellation_date | 건축년도: construction_year | 건물용도: building_purpose | 신고구분: report_type | 중개사시군구명: realtor_district_name");
						while (!validInput) {
							System.out.print("검색할 특정 컬럼을 입력: ");
							col = scanner.next();

							// 유효한 컬럼인지 확인
							for (String validColumn : validColumns) {
								if (col.equals(validColumn)) {
									validInput = true;
									break;
								}
							}

							if (!validInput) {
								System.out.println("유효하지 않은 컬럼입니다. 다시 입력해주세요.");
							}
						}
						System.out.print("검색할 내용을 입력: ");
						String prop = scanner.next();
						readController.findByPropertyDTO(col, prop);
					}
					}

					break;
				case 3:
					// 수정
					System.out.println("========================= 수정 =========================");
					break;
				case 4:
					// 삭제
					System.out.println("========================= 삭제 =========================");
					break;
				case 5:
					// 종료
					System.out.println("========================= 종료 =========================");
					System.out.println("프로그램을 종료합니다.");
					scanner.close(); // Scanner 자원 해제
					return; // 프로그램 종료
				default:
					// 잘못된 입력
					System.out.println("잘못된 선택입니다. 1-5 사이의 숫자를 선택해주세요.");
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

	}

}
