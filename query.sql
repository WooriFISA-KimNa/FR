CREATE TABLE real_estate_data (
    eid NUMBER PRIMARY KEY,
    reception_year NUMBER,          -- 접수연도
    district_code NUMBER,           -- 자치구코드
    district_name VARCHAR2(100),    -- 자치구명
    legal_dong_code NUMBER,         -- 법정동코드
    legal_dong_name VARCHAR2(100),  -- 법정동명
    lot_type NUMBER,                -- 지번구분
    lot_type_name VARCHAR2(50),     -- 지번구분명
    main_lot NUMBER,                -- 본번
    sub_lot NUMBER,                 -- 부번
    building_name VARCHAR2(200),    -- 건물명
    contract_date DATE,             -- 계약일
    property_price NUMBER,          -- 물건금액
    building_area NUMBER,           -- 건물면적
    land_area NUMBER,               -- 토지면적
    floor NUMBER,                   -- 층
    right_type VARCHAR2(50),        -- 권리구분
    cancellation_date DATE,         -- 취소일
    construction_year NUMBER(4),    -- 건축년도
    building_purpose VARCHAR2(100), -- 건물용도
    report_type VARCHAR2(50),       -- 신고구분
    realtor_district_name VARCHAR2(100) -- 중개사시군구명
);

CREATE SEQUENCE example_seq
    START WITH 1       -- 시작 값
    INCREMENT BY 1     -- 증가 간격
    NOCACHE;           -- 캐싱 사용 안 함 (기본값은 20)

CREATE OR REPLACE TRIGGER example_trigger
BEFORE INSERT ON real_estate_data
FOR EACH ROW
BEGIN
    IF :NEW.eid IS NULL THEN
SELECT example_seq.NEXTVAL INTO :NEW.eid FROM DUAL;
END IF;
END;
/

DROP TABLE real_estate_data;

SELECT count(*) FROM real_estate_data;