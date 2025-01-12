# FR
Real Estate Real transaction data

## Contributors

| ![김창규](https://avatars.githubusercontent.com/u/40711682?v=4) | ![김창성](https://avatars.githubusercontent.com/u/103468518?v=4) | ![나원호](https://avatars.githubusercontent.com/u/74342019?v=4) | ![나홍찬](https://avatars.githubusercontent.com/u/95984922?v=4) |
|:---------------------------------------------------------------:|:---------------------------------------------------------------:|:---------------------------------------------------------------:|:---------------------------------------------------------------:|
| [김창규](https://github.com/kcklkb)                         | [김창성](https://github.com/kcs19)                      | [나원호](https://github.com/CooolRyan)                         | [나홍찬](https://github.com/HongChan1412)                         |

## 사용 기술
<img src="https://img.icons8.com/?size=100&id=39913&format=png&color=000000/Oracle?style=for-the-badge&logo=Oracle&logoColor=white"><img src="https://img.icons8.com/?size=100&id=13679&format=png&color=000000/Java?style=for-the-badge&logo=Java&logoColor=white">

## 실헹환경
- Java 17
- Oracle 11 EE
- Ubuntu 24.04.1



## 프로젝트 소개
[공공데이터포털](https://www.data.go.kr/)에 존재하는 부동산 실거래 데이터 csv파일을 기반으로 테이블 설계 및 CRUD 작업을 진행할 수 있도록 프로젝트를 구성.
Virtual Box에 존재하는 Ubuntu 상의 도커 컨테이너의 Oracle DB를 연동하고 JDBC를 사용해 테이블 조작


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
SpringBoot에 존재하는 AOP의 개념을 일반 자바 프로그램에도 적용시키고자 노력, 이를 통해 쿼리 동작 시간 확인 및 효율적인 코드 작성 고려 가능
기존에 존재하던 코드에서 Controller 내부 함수에서 start_time 측정 시작, Repository 실행 완료 후 end_time 측정으로 시간 측정으로 비정확한 시간 측정.
Lambda 표현식을 통한 Repository 내 try문 내에서 쿼리 실행시간 측정 진행

## 실행 화면
<img width="834" alt="image" src="https://github.com/user-attachments/assets/c1222ac9-48b3-4fba-8944-6231cbc76530" />



## 쿼리 실행 시간 비교



## 추가 개선 사항
- 10만 개의 데이터 가용을 위한 효율성 고려해 쿼리 튜닝



