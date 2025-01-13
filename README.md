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
MySQL의 특정 컬럼에 대한 auto increment 기능이 Oracle 12 버전에서부터 generated as identity 라는 명령어로 존재.
하지만 현재 사용하고 있는 DB는 Oracle 11 버전이기 때문에 이를 사용할 수 없어 시퀀스를 생성하고 테이블에 Trigger를 작성해 수행함으로써 자동 인덱스 생성.


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
SpringBoot에 존재하는 AOP의 개념에서 착안해 Read 로직 관련 쿼리 동작 시간 확인 및 효율적인 코드 작성 고려하고자 함.
기존에 존재하던 코드에서 Controller 내부 함수에서 start_time 측정 시작, Repository 실행 완료 후 end_time 측정으로 시간 측정으로 비정확한 시간 측정.
Lambda 표현식을 통한 Repository 내 try문 내에서 쿼리 실행시간 측정 진행

## 실행 화면
<img width="834" alt="image" src="https://github.com/user-attachments/assets/c1222ac9-48b3-4fba-8944-6231cbc76530" />



## 쿼리 실행 시간 비교


## 추가 개선 사항
- 10만 개의 데이터 가용을 위한 효율성 고려해 쿼리 튜닝

## Trouble Shooting


