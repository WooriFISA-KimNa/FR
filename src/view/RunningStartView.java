package view;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.CreateController;
import controller.DeleteController;
import controller.ReadController;
import controller.UpdateController;
import dto.RealDTO;
import repository.ReadRepository;
import repository.UpdateRepository;


public class RunningStartView {

	public static void main(String[] args) {
		ReadRepository readRepository = new ReadRepository();
		ReadController readController = new ReadController(readRepository);
		UpdateRepository updateRepository = new UpdateRepository();
		UpdateController updateController = new UpdateController(updateRepository);


		Scanner scanner = new Scanner(System.in);
		String[] validColumns = { "reception_year", "district_code", "district_name", "legal_dong_code",
				"legal_dong_name", "lot_type", "lot_type_name", "main_lot", "sub_lot", "building_name", "contract_date",
				"property_price", "building_area", "land_area", "floor", "right_type", "cancellation_date",
				"construction_year", "building_purpose", "report_type", "realtor_district_name" };
		String col = "", property = "", mainLot = "", subLot = "", option = "";
		String choice; // 사용자가 선택할 메뉴 번호
		List<String> validColumnsList = Arrays.asList("eid", "district_name", "legal_dong_name", "main_lot", "sub_lot", "building_name", "contract_date", "property_price");

		
		System.out.println("***** DB 테이블 생성 *****");
		CreateController.createTable();

		System.out.println("***** sequence 생성 *****");
		CreateController.createSequence();

		System.out.println("***** trigger 생성 *****");
		CreateController.createTrigger();

		System.out.println("***** 테이블에 데이터 생성 *****");
		long startTime = System.nanoTime();
		CreateController.insertData();
		long endTime = System.nanoTime(); // 실행 시간 측정 종료
        // 실행 시간 출력
        System.out.println("Query executed in " + ((endTime - startTime) / 1_000_000) + " milliseconds");
		



		System.out.println("""
				\n\n\n\n\n\n
				F)ffffff R)rrrrr
				F)       R)    rr
				F)fffff  R)  rrr
				F)       R) rr
				F)       R)   rr
				F)       R)    rr
				""");
		while (true)
			try {

				System.out.println("========================= 메뉴 =========================");
				System.out.println("실행할 옵션을 입력해주세요");
				System.out.println("1. 추가  |  2. 조회  |  3. 수정  |  4. 삭제  |  5.종료");
				System.out.print("실행할 옵션을 입력: ");

				choice = scanner.next();
				boolean validInput = false;

				switch (choice) {
					case "1":
						// 추가
						System.out.println("========================= 추가 메뉴 =========================");
						System.out.println("다음 데이터를 입력하세요:");

						// 입력 필드
						String[] fields = {
								"자치구명 (district_name)",
								"법정동명 (legal_dong_name)",
								"본번 (main_lot)",
								"부번 (sub_lot)",
								"건물명 (building_name)",
								"계약일 (contract_date, 형식: yyyy-MM-dd)",
								"물건금액 (property_price)",
								"건물면적 (building_area)",
								"층 (floor)",
								"취소일 (cancellation_date, 형식: yyyy-MM-dd)",
								"건축용도 (building_purpose)",
								"신고구분 (report_type)"
						};

						// 데이터를 저장할 DTO 객체
						RealDTO newProperty = new RealDTO();

						scanner.nextLine(); // 이전 입력 버퍼 정리
						for (String field : fields) {
							System.out.print(field + " 입력: ");
							String input = scanner.nextLine().trim();

							// 필드에 따라 데이터 매핑
							switch (field.split(" ")[1]) {
								case "(district_name)":
									newProperty.setDistrictName(input.isEmpty() ? null : input);
									break;
								case "(legal_dong_name)":
									newProperty.setLegalDongName(input.isEmpty() ? null : input);
									break;
								case "(main_lot)":
									newProperty.setMainLot(input.isEmpty() ? null : Long.parseLong(input));
									break;
								case "(sub_lot)":
									newProperty.setSubLot(input.isEmpty() ? null : Long.parseLong(input));
									break;
								case "(building_name)":
									newProperty.setBuildingName(input.isEmpty() ? null : input);
									break;
								case "(contract_date,":
									newProperty.setContractDate(input.isEmpty() ? null : LocalDate.parse(input));
									break;
								case "(property_price)":
									newProperty.setPropertyPrice(input.isEmpty() ? null : Long.parseLong(input));
									break;
								case "(building_area)":
									newProperty.setBuildingArea(input.isEmpty() ? null : Long.parseLong(input));
									break;
								case "(floor)":
									newProperty.setFloor(input.isEmpty() ? null : Long.parseLong(input));
									break;
								case "(cancellation_date,":
									newProperty.setCancellationDate(input.isEmpty() ? null : LocalDate.parse(input));
									break;
								case "(building_purpose)":
									newProperty.setBuildingPurpose(input.isEmpty() ? null : input);
									break;
								case "(report_type)":
									newProperty.setReportType(input.isEmpty() ? null : input);
									break;
								default:
									System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
							}
						}
						// 입력 데이터 확인
						System.out.println("========================= 입력 데이터 =========================");
						System.out.println(newProperty);
						System.out.print("이 데이터를 추가하시겠습니까? (Y/N): ");
						String confirmation = scanner.nextLine().trim().toUpperCase();

						if (confirmation.equals("Y")) {
							// eid는 자동 생성되므로 null로 설정
							newProperty.setEid(null);

							// 데이터 삽입
							CreateController.insertSingleData(newProperty);
						} else {
							System.out.println("추가 작업이 취소되었습니다.");
						}
						break;

					case "2":
					// 검색
					System.out.println("========================= 조회 메뉴 =========================");
					System.out.println("실행할 옵션을 입력해주세요");
					System.out.println("1. 모든 데이터 조회  |  2. 정확한 데이터 조회  |  3. 특정 컬럼에 기준으로 정렬해 데이터 조회 | 4. 특정 컬럼만 조회 | 5. 데이터 검색 조회");
					System.out.print("실행할 옵션을 입력: ");
					choice = scanner.next();
					switch (choice) {
						case "1":
							readController.readAllDTO();
							break;
						case "2": {
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
							break;
						}
						case "3":
							System.out.println("검색할 특정 컬럼을 입력해주세요 (reception_year, district_code, district_name)");
							System.out.println(
									"접수연도: reception_year | 자치구코드: district_code | 자치구명: district_name | 법정동코드: legal_dong_code | 법정동명: legal_dong_name");
							System.out.println(
									"지번구분: lot_type | 지번구분명: lot_type_name | 본번: main_lot | 부번: sub_lot | 건물명: building_name");
							System.out.println(
									"계약일: contract_date | 물건금액: property_price | 건물면적: building_area | 토지면적: land_area | 층: floor");
							System.out.println(
									"권리구분: right_type | 취소일: cancellation_date | 건축년도: construction_year | 건물용도: building_purpose | 신고구분: report_type | 중개사시군구명: realtor_district_name");

							
						    scanner.nextLine(); // 버퍼에 남아있는 줄바꿈 문자 제거

							while (!validInput) {
							    System.out.print("검색할 특정 컬럼을 입력 (쉼표로 구분하여 입력): ");
							    col = scanner.nextLine().trim(); // 공백 제거 및 전체 라인 입력받기

							    String[] columns = col.split(","); // 쉼표로 컬럼 구분
							    StringBuilder validColumnQuery = new StringBuilder();
							    boolean isValid = true; // 입력 전체의 유효성을 체크하기 위한 변수

							    for (String column : columns) {
							        column = column.trim(); // 각 컬럼의 공백 제거
							        if (validColumnsList.contains(column)) {
							            // 유효한 컬럼인 경우
							            if (validColumnQuery.length() > 0) {
							                validColumnQuery.append(", ");
							            }
							            validColumnQuery.append(column);
							        } else {
							            // 유효하지 않은 컬럼인 경우
							            System.out.println("유효하지 않은 컬럼명: " + column);
							            isValid = false;
							            break; // 루프 종료
							        }
							    }

							    if (isValid) {
							        validInput = true; // 입력이 모두 유효한 경우
							        System.out.println("내림차순 : 1 / 오름차순 : 그 외 입력");
							        String order = scanner.nextLine();
							        readController.orderByColumn(validColumnQuery.toString(), order);
							    } else {
							        System.out.println("유효하지 않은 입력이 포함되어 있습니다. 다시 입력해주세요.");
							    }
							}

							break;
						case "4":
							System.out.println("검색할 특정 컬럼을 입력해주세요 (접수연도 검색-> reception_year 입력)");
							System.out.println(
									"접수연도: reception_year | 자치구코드: district_code | 자치구명: district_name | 법정동코드: legal_dong_code | 법정동명: legal_dong_name");
							System.out.println(
									"지번구분: lot_type | 지번구분명: lot_type_name | 본번: main_lot | 부번: sub_lot | 건물명: building_name");
							System.out.println(
									"계약일: contract_date | 물건금액: property_price | 건물면적: building_area | 토지면적: land_area | 층: floor");
							System.out.println(
									"권리구분: right_type | 취소일: cancellation_date | 건축년도: construction_year | 건물용도: building_purpose | 신고구분: report_type | 중개사시군구명: realtor_district_name");
							System.out.print("쉼표(,)로 구분하여 원하는 컬럼명을 입력하세요: ");

							scanner.nextLine(); // 이전 입력 버퍼 정리
							col = scanner.nextLine();

							// 입력된 컬럼을 쉼표로 분리
							String[] columns = col.split(",");
							StringBuilder validColumnQuery = new StringBuilder();

							for (String column : columns) {
								column = column.trim(); // 공백 제거
								if (validColumnsList.contains(column)) {
									if (validColumnQuery.length() > 0) {
										validColumnQuery.append(", ");
									}
									validColumnQuery.append(column);
								} else {
									System.out.println("유효하지 않은 컬럼명: " + column);
								}
							}

							if (validColumnQuery.length() == 0) {
								System.out.println("유효하지 않은 컬럼입니다. 다시 입력해주세요.");
								break;
							}

							//System.out.println("선택된 컬럼: " + validColumnQuery);

							// 특정 컬럼으로 조회
							readController.selectSpecificColumns(validColumnQuery.toString());
							break;
						case "5":
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
							readController.findByAnonymousPropertyDTO(col, prop);
							break;
					}

					break;
				case "3":
					// 수정
					System.out.println("========================= 수정 =========================");
					System.out.println("수정할 특정 컬럼을 입력해주세요 (자치구명 검색 후 수정-> district_name 입력)");
					System.out.println(
							"자치구명: district_name | 법정동명: legal_dong_name | 본번: main_lot | 부번: sub_lot | 건물명: building_name");
					System.out.println(
							"계약일: contract_date | 물건금액: property_price | 건물면적: building_area | 층: floor | 취소일: cancellation_date");
					System.out.println(
							"건축용도: building_purpose | 신고구분: report_type");
					System.out.print("수정을 위해 조회할 특정 컬럼을 입력해주세요: ");
					col = scanner.next();
					System.out.print("조회할 데이터의 조건을 입력해주세요: ");
					property = scanner.next();
					updateController.update(col, property);
					break;
				case "4":
					// 삭제
					System.out.println("========================= 삭제 =========================");
					System.out.print("삭제할 본번 입력해주세요: ");
					mainLot = scanner.next();
					System.out.print("삭제할 부번 입력해주세요: ");
					subLot = scanner.next();

					DeleteController.selectEstate(mainLot, subLot);
					System.out.print("삭제하시겠습니까 ? (Y) : ");
					option = scanner.next();
					
					if (option == "Y") {
						DeleteController.deleteEstate(mainLot, subLot);
						DeleteController.selectEstate(mainLot, subLot);
						System.out.println("삭제 성공");
					} else {
						System.out.println("삭제 실패");
					}
					break;
				case "5":
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
