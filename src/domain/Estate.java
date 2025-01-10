package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estate {
   // private Long eid; // PRIMARY KEY
    private Long receptionYear; // 접수연도
    private Long districtCode; // 자치구코드
    private String districtName; // 자치구명
    private Long legalDongCode; // 법정동코드
    private String legalDongName; // 법정동명
    private Long lotType; // 지번구분
    private String lotTypeName; // 지번구분명
    private Long mainLot; // 본번
    private Long subLot; // 부번
    private String buildingName; // 건물명
    private LocalDate contractDate; // 계약일
    private Long propertyPrice; // 물건금액
    private Long buildingArea; // 건물면적
    private Long landArea; // 토지면적
    private Long floor; // 층
    private String rightType; // 권리구분
    private LocalDate cancellationDate; // 취소일
    private Long constructionYear; // 건축년도
    private String buildingPurpose; // 건축용도
    private String reportType; // 신고구분
    private String realtorDistrictName; // 중개사소재지구명

}
