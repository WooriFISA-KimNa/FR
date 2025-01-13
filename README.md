# 🏘️ FR : For Real For Real Estate
Real Estate Real transaction data


## 프로젝트 소개
[공공데이터포털](https://www.data.go.kr/)에 존재하는 부동산 실거래 데이터 csv파일을 기반으로 테이블 설계 및 CRUD 작업을 진행할 수 있도록 프로젝트를 구성.
Virtual Box에 존재하는 Ubuntu 상의 도커 컨테이너의 Oracle DB를 연동하고 JDBC를 사용해 테이블 조작


## 목차 
- [Contributors](#contributors)
- [개발 환경](#개발-환경)
- [아키텍처 구조](#아키텍처-구조)
- [프로젝트 소개](#프로젝트-소개)
- [Flow Chart](#flow-chart)
- [주요 코드](#주요-코드)
- [REFACTORING](#refactoring)
- [실행 화면](#실행-화면)
- [쿼리 실행 시간 비교](#쿼리-실행-시간-비교)
- [추가 개선 사항](#추가-개선-사항)
- [Trouble Shooting](#Trouble-Shooting)

---

## Contributors

| ![김창규](https://avatars.githubusercontent.com/u/40711682?v=4) | ![김창성](https://avatars.githubusercontent.com/u/103468518?v=4) | ![나원호](https://avatars.githubusercontent.com/u/74342019?v=4) | ![나홍찬](https://avatars.githubusercontent.com/u/95984922?v=4) |
|:---------------------------------------------------------------:|:---------------------------------------------------------------:|:---------------------------------------------------------------:|:---------------------------------------------------------------:|
| [김창규](https://github.com/kcklkb)                         | [김창성](https://github.com/kcs19)                      | [나원호](https://github.com/CooolRyan)                         | [나홍찬](https://github.com/HongChan1412)                         |

## 개발 환경
- Java 17
- Oracle 11 EE
- Ubuntu 24.04.1


## 아키텍처 구조
![image](https://github.com/user-attachments/assets/1c4ce90d-af91-47fb-8d48-b45b45939873)


## 프로젝트 파일 구조
<table>
  <tr>
    <td>
      <img src="https://github.com/user-attachments/assets/5dbc3534-b708-4812-9745-7e15199260e2" alt="image" width="900"/>
    </td>
    <td>
      <strong>1. C, R, U, D 각각의 컨트롤러 및 DAO 파일 분할</strong>
       <ul>
        <li>하나의 테이블만을 사용하므로 각 기능을 담당하는 사람이 한 명씩 맡아 구현할 수 있도록 파일을 분할했습니다.</li>
      </ul>
      <ul>
        <li>각자의 역할에 맞는 기능을 담당하는 파일을 분리함으로써 코드의 책임을 명확히 하고, 작업 분담을 용이하게 하여 효율적인 협업을 도왔습니다.</li>
      </ul>
      <strong>2. DB 접속 정보 분할 (dbinfo.properties)</strong> 
      <ul>
        <li>DB 접속 정보를 별도의 dbinfo.properties 파일로 분리하여 하드코딩을 방지하고, 보안과 환경 설정 관리를 용이하게 했습니다.</li>
      </ul>
      <ul>
        <li>이 파일을 .gitignore에 추가하여, 소스 코드와 DB 접속 정보를 분리하여 Git에 관리되지 않도록 하여 보안을 강화하고, 민감한 정보가 노출되지 않도록 했습니다.</li>
      </ul>
      <strong>3. Util 파일 생성</strong>
	<ul>
        <li>CRUD 작업에 공통적으로 필요한 DB 연결 및 자원 해제(close) 함수를 static 메소드로 Util 클래스를 통해 분리하여 코드의 중복을 최소화하고 재사용성을 높였습니다.</li>
      </ul>
      <ul>
        <li>데이터 유효성 체크 함수를 별도로 분리하여 재사용이 가능하도록 구현함으로써, 각 기능에서 데이터 처리의 일관성을 유지하고, 데이터의 품질을 관리할 수 있었습니다.</li>
      </ul>
    </td>
  </tr>
</table>



- 인덱스 관련 테이블 설정
MySQL의 특정 컬럼에 대한 **auto increment** 기능이 **Oracle 12 버전에서부터 generated as identity 라는 명령어로 존재.**
하지만 현재 사용하고 있는 DB는 **Oracle 11** 버전이기 때문에 이를 사용할 수 없어 **시퀀스를 생성하고 테이블에 Trigger를 작성해 수행함**으로써 자동 인덱스 생성.


- 테이블 전처리
테이블 전처리의 초반 목표 : 테이블 생성 -> 데이터 삽입 -> 사용하지 않는 컬럼 드랍 -> null 값 존재하는 row 드랍


시간 상의 이유로 구현된 전처리 : 테이블 생성 -> 데이터 삽입 -> 일부 컬럼 사용을 위한 DTO 생성


실제 프로그램에서만 사용하는 컬럼들만 사용할 수 있도록 RealDTO 객체를 통해 데이터를 주고 받으며 프로그램이 동작할 수 있도록 코드 작성.



1. 삽입
  - RealDTO에 존재하는 컬럼들에 대한 데이터 삽입.


2. 조회
  - 모든 데이터 조회
  - 특정 조건 조회(정확한 데이터 입력)
  - 특정 조건 조회(데이터 일부만 입력, Like 사용)
  - 특정 컬럼 선택 조회
  - 정렬 후 조회


3. 수정
  - 수정할 컬럼 조회후 조건 입력 후 수정


4. 삭제
  - 본번, 부번에 해당하는 데이터 삭제

## Flow Chart
<img width="868" alt="image" src="https://github.com/user-attachments/assets/d269687f-052e-4872-a15c-3240e73b5049" />




## 주요 코드


## REFACTORING
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
SpringBoot에 존재하는 AOP의 개념에서 착안해 Read 로직 관련 쿼리 동작 시간 확인 및 효율적인 코드 작성 고려하고자 함.
기존에 존재하던 코드에서 Controller 내부 함수에서 start_time 측정 시작, Repository 실행 완료 후 end_time 측정으로 시간 측정으로 비정확한 시간 측정.
Lambda 표현식을 통한 Repository 내 try문 내에서 쿼리 실행시간 측정 진행

## 실행 화면
<img width="834" alt="image" src="https://github.com/user-attachments/assets/c1222ac9-48b3-4fba-8944-6231cbc76530" />



## 쿼리 실행 시간 비교


## 추가 개선 사항
- 10만 개의 데이터 가용을 위한 효율성 고려해 쿼리 튜닝

## Trouble Shooting

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


### **UPDATE 로직 개선 1**
#### 문제 설명
> 모든 데이터를 조회한 후 수정하는 방식에서는 많은 데이터가 조회되어 성능에 부담을 줄 수 있음.

#### 해결 방안
- 조회할 데이터의 컬럼과 조건을 입력받아 필요한 데이터만 조회하도록 수정

#### 결과
- 필요한 데이터만 조회하여 불필요한 데이터 처리 부담을 줄일 수 있었음

### **UPDATE 로직 개선 2**
#### 문제 설명
> 하나의 row에 대해 모든 정보를 입력받아 데이터를 구분하고 수정하는 방식은 사용자가 많은 입력을 해야 하기 때문에 불편함

#### 해결 방안
- 각 데이터를 구분하기 위해 인덱스 값을 부여하여, 인덱스 값만 받아 데이터 구분 및 수정할 수 있도록 변경

#### 결과
- 사용자 입력 부담을 줄이고 수정 작업이 간편해짐


## 프로젝트 고찰 및 회고
 - 배운 점


MVC 패턴을 적용하여 Java JDBC와 Oracle DB를 활용해 CRUD기능을 구현할 수 있었습니다. 이 과정에서 전체적인 아키텍쳐와 개발 프로세스를 명확하게 구분하고, 각 부분이 담당하는 역할을 집중적으로 구현할 수 있었습니다. 


또한, 진행 중에는 CSV파일 기반으로 10만건의 데이터를 insert할 수 있었고, 구현한 시스템을 바탕으로 쿼리 성능 개선과 예외 처리를 강화하여 더 복잡하고 대용량 데이터처리에서도 안정성을 유지할 수 있도록 할 것입니다.


 - 아쉬운 점


데이터 전처리 과정에서 사용빈도가 낮고 null값이 많은 row들을 sql로 처리하려 했으나, 데이터가 대용량으로 시간상 CSV 파일에서 임의로 처리했는데, 이 부분이 아쉬웠으며, 추후 고도화할 여지가 있음을 느꼈습니다.
