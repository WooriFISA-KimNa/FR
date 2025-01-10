package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealDTO {
    // 자치구명, 법정동명, 본번, 부번, 건물명, 계약일, 물건금액, 건물면적, 층, 취소일, 건물용도, 신고구분 + 평 수,
    //private Long eid; // PRIMARY KEY
    private String districtName; // 자치구명
    private String legalDongName; // 법정동명
    private Long mainLot; // 본번
    private Long subLot; // 부번
    private String buildingName; // 건물명
    private LocalDate contractDate; // 계약일
    private Long propertyPrice; // 물건금액
    private Long buildingArea; // 건물면적
    private Long floor; // 층
    private LocalDate cancellationDate; // 취소일
    private String buildingPurpose; // 건축용도
    private String reportType; // 신고구분

}


