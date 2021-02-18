/**********************************/
/* Table Name: survey */
/**********************************/
DROP TABLE survey;

CREATE TABLE survey(
survey_no             NUMBER(10)          NOT NULL                PRIMARY KEY,
survey_head          VARCHAR(100)      DEFAULT   '설문조사'   NOT NULL,
rdate                   DATE                    NOT NULL,
survey_count        NUMBER(10)          DEFAULT     0             NOT NULL,
survey_passwd      VARCHAR2(15)      DEFAULT    '1234'       NOT NULL
);

COMMENT ON TABLE survey is '설문조사';
COMMENT ON COLUMN survey.survey_no is '등록번호';
COMMENT ON COLUMN survey.survey_head is '제목';
COMMENT ON COLUMN survey.rdate is '등록일';
COMMENT ON COLUMN survey.survey_count is '조회수';
COMMENT ON COLUMN survey.survey_passwd is '비밀번호';

DROP SEQUENCE survey_seq;
CREATE SEQUENCE survey_seq
  START WITH 1        -- 시작 번호
  INCREMENT BY 1      -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2             -- 2번은 메모리에서만 계산
  NOCYCLE;            -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO survey(survey_no, survey_head, rdate, survey_count, survey_passwd)
VALUES(survey_seq.nextval, '설문조사',  sysdate, 0, '1234'); 

INSERT INTO survey(survey_no, survey_head, rdate, survey_count, survey_passwd)
VALUES(survey_seq.nextval, '설문조사2', sysdate, 0, '1234'); 

INSERT INTO survey(survey_no, survey_head,  rdate, survey_count, survey_passwd)
VALUES(survey_seq.nextval, '설문조사3',  sysdate, 0, '1234'); 

SELECT * FROM survey;

COMMIT;
-- 목록
SELECT survey_no, survey_head,  rdate, survey_count
FROM survey 
ORDER BY survey_no ASC; 

-- 조회
SELECT survey_no, survey_head, rdate, survey_count
FROM survey
WHERE survey_no=3;

-- 수정
UPDATE survey
SET survey_head='설문조사4☆'
WHERE survey_no=4;

-- read시 조회수 증가
UPDATE survey
SET survey_count = survey_count + 1
WHERE survey_no=1;

COMMIT;

/**********************************/
/* Table Name: survey_item     */ 
/**********************************/
-- 설문조사 항목 테이블

DROP TABLE survey_item; 
CREATE TABLE survey_item(
    survey_itemno                 NUMBER(10)     NOT NULL    PRIMARY KEY,
    survey_no                       NUMBER(10)     NOT NULL,
    item                              VARCHAR2(200)    NOT NULL,
    item_count                     NUMBER(7) DEFAULT 0  NOT NULL,
  FOREIGN KEY (survey_no) REFERENCES survey (survey_no)
);

COMMENT ON TABLE surveyitem is '설문 조사 항목';
COMMENT ON COLUMN survey_item.survey_itemno is '설문 조사 항목 번호';
COMMENT ON COLUMN survey_item.surveyno is '설문 조사 번호';
COMMENT ON COLUMN survey_item.item is '항목';
COMMENT ON COLUMN survey_item.item_count is '항목 선택 수';

DROP SEQUENCE item_seq;
CREATE SEQUENCE item_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
