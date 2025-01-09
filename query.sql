CREATE TABLE real_estate_data (
                                eid NUMBER PRIMARY KEY,
                                접수연도 NUMBER,
                                자치구코드 NUMBER,
                                자치구명 VARCHAR2(100),
                                법정동코드 NUMBER,
                                법정동명 VARCHAR2(100),
                                지번구분 NUMBER,
                                지번구분명 VARCHAR2(50),
                                본번 NUMBER,
                                부번 NUMBER,
                                건물명 VARCHAR2(200),
                                계약일 DATE,
                                물건금액 NUMBER,
                                건물면적 NUMBER,
                                토지면적 NUMBER,
                                층 NUMBER,
                                권리구분 VARCHAR2(50),
                                취소일 DATE,
                                건축년도 NUMBER(4), -- DATE에서 NUMBER로 변경
                                건물용도 VARCHAR2(100),
                                신고구분 VARCHAR2(50),
                                중개사시군구명 VARCHAR2(100)
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