# 🏘️ FR : For Real Data For Real Estate
Real Estate Real transaction data

<br>

## 📖프로젝트 소개
CRUD 기능을 구현하는 프로젝트의 특성에서 최대한 많은 데이터를 사용하고자 하는 목표에서 착안해 [공공데이터포털](https://www.data.go.kr/)에 존재하는 서울특별시 부동산 실거래 데이터를 주제로 선정.
전체 데이터는 너무 많은 관계로 2024년 거래 데이터 약 10만건만을 추출해서 사용.
CSV 파일을 기반으로 테이블 설계 및 CRUD 작업을 진행할 수 있도록 프로젝트를 구성.
Virtual Box에 존재하는 Ubuntu 상의 도커 컨테이너의 Oracle DB를 연동하고 JDBC를 사용해 테이블 조작.

<br>

## 📚 활용 데이터

### 1. [공공데이터 포탈] 서울특별시_부동산 실거래가 정보
- **링크**: [공공데이터 포탈 서울특별시_부동산 실거래가 정보](https://www.data.go.kr/data/15052419/fileData.do)

### 2. 사용한 데이터 컬럼
- `eid`: 고유 ID
- `reception_year`: 접수연도
- `district_code`: 자치구 코드
- `district_name`: 자치구명
- `legal_dong_code`: 법정동 코드
- `legal_dong_name`: 법정동명
- `lot_type`: 지번 구분
- `lot_type_name`: 지번 구분명
- `main_lot`: 본번
- `sub_lot`: 부번
- `building_name`: 건물명
- `contract_date`: 계약일
- `property_price`: 물건 금액
- `building_area`: 건물 면적
- `land_area`: 토지 면적
- `floor`: 층
- `right_type`: 권리 구분
- `cancellation_date`: 취소일
- `construction_year`: 건축 년도
- `building_purpose`: 건물 용도
- `report_type`: 신고 구분
- `realtor_district_name`: 중개사 시군구명



## 📑목차 
- [Contributors](#contributors)
- [개발 환경](#개발-환경)
- [아키텍처 구조](#아키텍처-구조)
- [프로젝트 파일 구조](#프로젝트-파일-구조)
- [프로젝트 기능](#프로젝트-기능)
- [Flow Chart](#flow-chart)
- [REFACTORING](#refactoring)
- [실행 화면](#실행-화면)
- [Trouble Shooting](#Trouble-Shooting)
- [쿼리 실행 시간 비교](#쿼리-실행-시간-비교)
- [추가 개선 사항](#추가-개선-사항)
- [프로젝트 고찰 및 회고](#프로젝트-고찰-및-회고)
---

<br>

## 👥Contributors

| ![김창규](https://avatars.githubusercontent.com/u/40711682?v=4) | ![김창성](https://avatars.githubusercontent.com/u/103468518?v=4) | ![나원호](https://avatars.githubusercontent.com/u/74342019?v=4) | ![나홍찬](https://avatars.githubusercontent.com/u/95984922?v=4) |
|:---------------------------------------------------------------:|:---------------------------------------------------------------:|:---------------------------------------------------------------:|:---------------------------------------------------------------:|
| [김창규](https://github.com/kcklkb)                         | [김창성](https://github.com/kcs19)                      | [나원호](https://github.com/CooolRyan)                         | [나홍찬](https://github.com/HongChan1412)                         |

<br>

## 💻개발 환경
- Java 17
- Oracle 11 EE
- Ubuntu 24.04.1

<br>

## 🏗️아키텍처 구조
![image](https://github.com/user-attachments/assets/1c4ce90d-af91-47fb-8d48-b45b45939873)

<br>

## 📂프로젝트 파일 구조
<table>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/5dbc3534-b708-4812-9745-7e15199260e2" alt="image" width="900"/>
    </td>
    <td>
      <strong>1. C, R, U, D 각각의 컨트롤러 및 DAO 파일 분할</strong>
       <ul>
        <li>하나의 테이블만을 사용하므로 각 기능을 담당하는 사람이 한 명씩 맡아 구현할 수 있도록 파일 분할.</li>
      </ul>
      <ul>
        <li>각자의 역할에 맞는 기능을 담당하는 파일을 분리함으로써 코드의 책임을 명확히 하고, 작업 분담을 용이하게 하여 효율적인 협업을 추구.</li>
      </ul>
      <strong>2. DB 접속 정보 분할 (dbinfo.properties)</strong> 
      <ul>
        <li>DB 접속 정보를 별도의 dbinfo.properties 파일로 분리하여 하드코딩을 방지하고, 보안과 환경 설정 관리를 용이하게 함.</li>
      </ul>
      <ul>
        <li>이 파일을 .gitignore에 추가하여, 소스 코드와 DB 접속 정보를 분리하여 Git에 관리되지 않도록 하여 보안을 강화하고, 민감한 정보가 노출되지 않도록 함.</li>
      </ul>
      <strong>3. Util 파일 생성</strong>
	<ul>
        <li>CRUD 작업에 공통적으로 필요한 DB 연결 및 자원 해제(close) 함수를 static 메소드로 Util 클래스를 통해 분리하여 코드의 중복을 최소화하고 재사용성을 높임.</li>
      </ul>
      <ul>
        <li>데이터 유효성 체크 함수를 별도로 분리하여 재사용이 가능하도록 구현함으로써, 각 기능에서 데이터 처리의 일관성을 유지하고, 데이터의 품질을 관리함.</li>
      </ul>
    </td>
  </tr>
</table>

<br>

## ⚙️프로젝트 기능

- 인덱스 관련 테이블 설정
MySQL의 특정 컬럼에 대한 **auto increment** 기능이 **Oracle 12 버전에서부터 generated as identity 라는 명령어로 존재.**
하지만 현재 사용하고 있는 DB는 **Oracle 11** 버전이기 때문에 이를 사용할 수 없어 **시퀀스를 생성하고 테이블에 Trigger를 작성해 수행함**으로써 자동 인덱스 생성.


- 테이블 전처리
테이블 전처리의 초반 목표 : 테이블 생성 -> 데이터 삽입 -> 사용하지 않는 컬럼 드랍 -> null 값 존재하는 row 드랍


- 시간 상의 이유로 구현된 전처리 : 테이블 생성 -> 데이터 삽입 -> 일부 컬럼 사용을 위한 DTO 생성


- 실제 프로그램에서만 사용하는 컬럼들만 사용할 수 있도록 RealDTO 객체를 통해 데이터를 주고 받으며 프로그램이 동작할 수 있도록 코드 작성.

  


- 데이터 예시
| eid  | reception_year | district_code | district_name | legal_dong_code | legal_dong_name | lot_type | lot_type_name | main_lot | sub_lot | building_name | contract_date | property_price | building_area | land_area | floor | right_type | cancellation_date | construction_year | building_purpose | report_type | realtor_district_name |
| ---- | -------------- | ------------- | ------------- | --------------- | --------------- | -------- | ------------- | -------- | ------- | ------------- | ------------- | -------------- | -------------- | --------- | ----- | ---------- | ------------------ | ------------------ | ----------------- | ----------- | ---------------------- |
| 1 | 2024           | 11230         | 동대문구      | 10400           | 전농동          | 1        | 대지          | 103      | 50      | (103-50)      | 2023-12-31    | 24000          | 44.04          | 20        | -1    |    입주권      |        2024-12-31    | 1991               | 연립다세대         | 중개거래     | 서울 동대문구            |




- RealDTO
| eid  | district_name | legal_dong_name | main_lot | sub_lot | building_name | contract_date | property_price | building_area | floor | cancellation_date | building_purpose | report_type |
| ---- | ------------ | ------------- | ------- | ------ | ------------ | ------------ | ------------- | ------------ | ----- | ---------------- | --------------- | ---------- |
| 1    | 동대문구     | 전농동        | 103     | 50     | (103-50)     | 2023-12-31   | 24000         | 44           | -1    | 2024-12-31       | 연립다세대      | 중개거래    |




<br>
<br>

| **기능**  | **설명**                                                                                      |
|-----------|-----------------------------------------------------------------------------------------------|
| **삽입**  | RealDTO에 존재하는 컬럼들에 대한 데이터 삽입                                                   |
| **조회**  | 모든 데이터 조회                                                                               |
|           | 특정 조건 조회 (정확한 데이터 입력)                                                            |
|           | 특정 조건 조회 (데이터 일부만 입력, Like 사용)                                                 |
|           | 특정 컬럼 선택 조회                                                                            |
|           | 정렬 후 조회                                                                                   |
| **수정**  | 수정할 컬럼 조회 후 조건 입력 후 수정                                                          |
| **삭제**  | 본번, 부번에 해당하는 데이터 삭제        

<br>

## 🔄Flow Chart
<img width="868" alt="image" src="https://github.com/user-attachments/assets/d269687f-052e-4872-a15c-3240e73b5049" />

<br>
<br>

## 🔧REFACTORING


StreamAPI와 Lambda 표현식을 학습한 이후 활용한 코드 리팩토링

```java
//기존 EndView
for (List<Object> row : results) {
            for (Object value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
```


```java
//StreamAPI 사용한 Endview
results.stream()
	       .forEach(row -> {
	           row.stream()
	               .forEach(value -> System.out.print(value + "\t"));
	           System.out.println();
	       });
```


SpringBoot에 존재하는 AOP의 개념에서 착안해 Read 로직 관련 쿼리 동작 시간 확인 및 쿼리 튜닝을 고려하고자 함.


기존에 존재하던 코드에서 Controller 함수 내부에서 start_time 측정 시작, Repository 실행 완료 후 end_time 측정으로 총 execution time 측정.


Java Dynamic Proxy를 활용해 시간 측정 관련 로직을 공통으로 사용할 수 있도록 코드 작성.


CRUD 작업을 진행하면서 크게 필요를 느끼지 못해 Service 계층을 생략했던 코드 구조를 변경해 Service 계층을 거치기 전 프록시 계층을 추가.

![가 - visual selection](https://github.com/user-attachments/assets/e083217f-ab08-41ee-91ec-cbf05dc2f81a)




```
package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


// 동적 프록시 핸들러
public class AOPUtil implements InvocationHandler {
    private final Object target;

    public AOPUtil(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args); // 실제 메서드 호출
        long endTime = System.nanoTime();
        System.out.println("Method [" + method.getName() + "] executed in " + ((endTime - startTime) / 1_000_000) + " milliseconds");
        return result;
    }

    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AOPUtil(target)
        );
    }
}
```


```
//생성자 주입 시 프록시 계층 추가
ReadRepository readRepository = new ReadRepository();
ReadServiceInterface myService = new ReadService(readRepository);
ReadServiceInterface proxyService = (ReadServiceInterface) AOPUtil.createProxy(myService);
// Controller 생성 (Service 주입)
ReadController readController = new ReadController(proxyService);
```

<br>

## 🖼️실행 화면
<details>
<summary>Result</summary>
<img width="834" alt="image" src="https://github.com/user-attachments/assets/c1222ac9-48b3-4fba-8944-6231cbc76530" />

![image](https://github.com/user-attachments/assets/2aa20e44-8cd5-411b-bb19-c24054177fb5)

![image](https://github.com/user-attachments/assets/a73ac39e-de07-4d4e-9816-55c611174a2b)

![image](https://github.com/user-attachments/assets/53e706b2-98d7-4662-acca-8020d524c178)

![image](https://github.com/user-attachments/assets/69cc9564-4138-4d44-89be-95f516fd1856)

![image](https://github.com/user-attachments/assets/bfa2905c-220b-4b10-b06a-ccf87e6dd329)
</details>

<br>

## 🛠️Trouble Shooting

### **CSV 데이터 파싱 오류 해결**

- 데이터베이스에 CSV 파일을 삽입하는 과정에서, 콤마로 구분된 데이터를 처리하기 위해 `split` 메서드를 사용하여 CSV 데이터를 파싱. 하지만, 일부 데이터 내에 콤마가 포함된 경우, 예상치 못한 컬럼 구분이 발생하여 데이터 파싱에 오류 발생.

#### 문제 설명
> 아래 사진과 같이 데이터 안에 콤마가 포함된 경우, 기본적인 `split` 방식으로 파싱을 진행하면 해당 데이터를 잘못 분리하여, 다른 컬럼으로 이동하는 문제가 발생.

<div style="display: flex;">
  <img src="https://github.com/user-attachments/assets/75f28eac-7a30-4989-a8d6-efea177a10c7" width="48%" style="margin-right: 4%;" />
  <img src="https://github.com/user-attachments/assets/cf05b858-bb10-495c-87c8-0ed4ec9802d1" width="48%" />
</div>

#### 해결 방안
- 이 문제를 해결하기 위해, Maven repository에서 제공하는 `opencsv` 라이브러리를 활용.
- 특히, `opencsv`는 텍스트 내에서 `"` (큰따옴표)로 감싸진 데이터가 콤마를 포함하더라도 이를 하나의 값으로 인식하여 정확하게 파싱가능.
- 또한, `opencsv`를 사용함으로써 CSV 파일을 처리하는 속도가 눈에 띄게 개선.

#### 결과
- `opencsv` 적용 후, CSV 데이터의 파싱 정확도 향상, 대량의 데이터도 빠르게 처리가능해, 데이터 입력 시간 단축.
---

### **NLS_DATE_FORMAT 오류 해결**

- Oracle 데이터베이스에 `date` 형식의 데이터를 입력하는 과정에서 예상치 못한 날짜 형식이 입력되는 문제가 발생.

#### 문제 설명
> 아래 사진과 같이 `2024-12-31`이라는 날짜를 입력했을 때, 데이터베이스에 `2024-12-31 00:00:00.000`이 아닌 `0024-12-31 00:00:00.000` 형식으로 잘못 저장되는 현상이 발생.
> 
![image](https://github.com/user-attachments/assets/52023565-0b0e-4287-a8b4-7c6f58cf9eeb)

#### 해결 방안
- 이 문제를 해결하기 위해, **JDBC**를 사용하여 데이터 입력 전 Oracle 데이터베이스의 세션에 대해 `NLS_DATE_FORMAT`을 명시적으로 설정.
- `ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD'` 명령어를 사용하여 날짜 포맷을 `YYYY-MM-DD`로 설정한 후, 데이터를 입력하면 날짜가 올바르게 `2024-12-31` 형식으로 입력되도록 함.

#### 결과
`ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD'` 명령어를 사용하여 세션의 날짜 포맷을 정확하게 설정한 결과, 데이터베이스에 날짜가 올바르게 입력되었고, 날짜 형식에 의한 오류를 해결

---
### **UPDATE 로직 개선 1**
#### 문제 설명
> 모든 데이터를 조회한 후 수정하는 방식에서는 많은 데이터가 조회되어 성능에 부담을 줄 수 있음.

#### 해결 방안
- 조회할 데이터의 컬럼과 조건을 입력받아 필요한 데이터만 조회하도록 수정

#### 결과
- 필요한 데이터만 조회하여 불필요한 데이터 처리 부담을 줄일 수 있었음
---

### **UPDATE 로직 개선 2**
#### 문제 설명
> 하나의 row에 대해 모든 정보를 입력받아 데이터를 구분하고 수정하는 방식은 사용자가 많은 입력을 해야 하기 때문에 불편함

#### 해결 방안
- 각 데이터를 구분하기 위해 인덱스 값을 부여하여, 인덱스 값만 받아 데이터 구분 및 수정할 수 있도록 변경

#### 결과
- 사용자 입력 부담을 줄이고 수정 작업이 간편해짐

<br>

## ⏱️쿼리 실행 시간 비교
#### Data Insertion
프로그램 실행 시 초기화 과정에서 Table 존재시 Drop 후 시퀀스와 트리거 생성 후 새로 Insert 하는 방식을 사용.


![image](https://github.com/user-attachments/assets/c518e09e-b2ae-4369-a210-5e2a2cfabe2a)


초기 데이터 Insertion 시간의 경우 평균 25s 소요


이후 트러블 슈팅을 해결하는 과정에서 OpenCSV를 사용하게 되고, Batch 작업 없이 Insertion 진행 시 excute time이 눈에 띄게 증가한 것을 확인.


![image](https://github.com/user-attachments/assets/f5f64d82-4433-456d-a25b-4552082950c0)


이를 해결하기 위해 Batch 작업 및 사이즈 조절을 통한 excute time 최적화 진행.


![image](https://github.com/user-attachments/assets/2024b7ad-2144-492e-8fe0-c50a06c39246)
![image](https://github.com/user-attachments/assets/9312bcea-7b92-4695-8bb1-91da3499a6f4)
![image](https://github.com/user-attachments/assets/9bb7bce6-0197-4073-b5f5-cdc358ac2f4e)
![image](https://github.com/user-attachments/assets/438836aa-b200-4875-9edb-249c506c0997)
![image](https://github.com/user-attachments/assets/5aa06f46-b333-466d-871f-c1f0682f046f)


이전 코드의 Batch size 500과 비교해 OpenCSV 사용 시 동일 과정에서 직접 파싱을 진행하던 이전 과정에 비해 절반 가량의 시간만 소요. 


이후 Batch size를 5000으로 설정하며 평균 7~8s로 시간을 줄일 수 있었음.

<br>

## 🔨추가 개선 사항
- 10만 개의 데이터 가용을 위한 효율성 고려해 쿼리 튜닝
- 완벽한 데이터 전처리를 위한 개선
- 데이터 훼손 방지 및 관리 효율성을 강화하기 위해 CSV 파일에 CRUD 작업을 적용하여 백업 관리와 데이터 내보내기 기능을 추가


<br>

## 📝프로젝트 고찰 및 회고
#### **나홍찬**
jdbc로 DB의 table, sequence, trigger와 입출력을 구현해보면서 Java의 기본문법과 DTO, DAO, MVC 패턴을 이해할 수 있었던 프로젝트였습니다.
Maven을 사용해 외부 라이브러리 사용법을 익혔고, 또한 StreamAPI로 Code Refactoring하는 방법을 알게되었습니다.


#### **김창규**
MVC 패턴을 적용하여 Java JDBC와 Oracle DB를 활용해 CRUD기능을 구현할 수 있었습니다. 이 과정에서 전체적인 아키텍쳐와 개발 프로세스를 명확하게 구분하고, 각 부분이 담당하는 역할을 집중적으로 구현할 수 있었습니다. 


또한, 진행 중에는 CSV파일 기반으로 10만건의 데이터를 insert할 수 있었고, 구현한 시스템을 바탕으로 쿼리 성능 개선과 예외 처리를 강화하여 더 복잡하고 대용량 데이터처리에서도 안정성을 유지할 수 있도록 할 것입니다.


데이터 전처리 과정에서 사용빈도가 낮고 null값이 많은 row들을 sql로 처리하려 했으나, 데이터가 대용량으로 시간상 CSV 파일에서 임의로 처리했는데, 이 부분이 아쉬웠으며, 추후 고도화할 여지가 있음을 느꼈습니다.


#### **나원호**
기존의 Spring 코드 구조에서 착안한 java를 사용한 MVC 구조 프로젝트를 만들면서 Spring 컨테이너에서 제공하는 기능과 일반 Java만 사용할 때의 차이점을 좀 더 와닿게 체험할 수 있었던 프로젝트였습니다.


Repository에서 생성자 주입을 받을 때 기존에 Datasource를 받던 과정을 DBUtil에서 Connection 객체를 받아오는 방식으로 구현하고 Spring AOP가 실제 구현되는 프록시의 개념을 직접 구현하는 과정을 통해 하위 레벨에서 동작하는 방식을 확인할 수 있었습니다.


또한 잦은 commit과 동기화의 진행으로 conflict 방지를 최소화 하고자 했습니다. 해당 프로젝트가 끝나고 Conflict 최소화를 위한 역할 분배에 대해 다시 고려해보고 싶습니다.


#### **김창성**
Docker를 사용해 DB를 실행하고 포트 포워딩을 통해 연결하는 과정에서, Windows에 설치된 DB와의 충돌 문제가 발생했습니다. 이를 해결하는 과정에서 Docker와 포트 포워딩 개념을 더욱 확실히 이해할 수 있었습니다. 

프로젝트 초기에는 DB 컬럼, 테이블 이름, DTO 및 도메인 간의 혼란으로 어려움을 겪었으며, 이는 프로젝트 진행에 불필요한 시간 소모를 초래했습니다. 이를 통해 처음부터 철저한 계획과 설계의 중요성을 깨달았습니다.

View와 Service를 한 번에 구현하면서 Service 코드가 지나치게 복잡해졌습니다. 앞으로는 View와 Service를 명확히 분리하여 코드의 가독성을 높이고, 더 간결하며 유지보수가 용이한 코드를 작성하기 위해 노력하겠습니다.
