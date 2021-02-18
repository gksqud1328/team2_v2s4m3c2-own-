/**********************************/
/* Table Name: survey */
/**********************************/
DROP TABLE survey;

CREATE TABLE survey(
survey_no             NUMBER(10)          NOT NULL                PRIMARY KEY,
survey_head          CLOB                    DEFAULT   '설문조사'   NOT NULL,
survey_content      CLOB                     DEFAULT   ' 내용'       NOT NULL,
rdate                   DATE                    NOT NULL,
survey_count        NUMBER(10)          DEFAULT     0             NOT NULL,
survey_passwd      VARCHAR2(15)      DEFAULT    '1234'       NOT NULL,
file1                    VARCHAR2(100)     NULL,
thumb1               VARCHAR2(100)     NULL,
size1                    NUMBER(10)         DEFAULT  0 NULL
);

COMMENT ON TABLE survey is '설문조사';
COMMENT ON COLUMN survey.survey_no is '등록번호';
COMMENT ON COLUMN survey.survey_head is '제목';
COMMENT ON COLUMN survey.survey_content is '내용';
COMMENT ON COLUMN survey.rdate is '등록일';
COMMENT ON COLUMN survey.survey_count is '조회수';
COMMENT ON COLUMN survey.survey_passwd is '비밀번호';
COMMENT ON COLUMN survey.file1 is '메인 이미지';
COMMENT ON COLUMN survey.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN survey.size1 is '메인 이미지 크기';

DROP SEQUENCE survey_seq;
CREATE SEQUENCE survey_seq
  START WITH 1        -- 시작 번호
  INCREMENT BY 1      -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2             -- 2번은 메모리에서만 계산
  NOCYCLE;            -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO survey(survey_no, survey_head, survey_content, rdate, survey_count, survey_passwd, file1, thumb1, size1)
VALUES(survey_seq.nextval, '설문조사', '가나다라', sysdate, 0, '1234', 'spring.jpg', 'spring_t.jpg', 23657); 

INSERT INTO survey(survey_no, survey_head, survey_content, rdate, survey_count, survey_passwd, file1, thumb1, size1)
VALUES(survey_seq.nextval, '설문조사2', 'abcd', sysdate, 0, '1234', 'spring.jpg', 'spring_t.jpg', 23657); 

INSERT INTO survey(survey_no, survey_head, survey_content, rdate, survey_count, survey_passwd, file1, thumb1, size1)
VALUES(survey_seq.nextval, '설문조사3', '설문..', sysdate, 0, '1234', 'spring.jpg', 'spring_t.jpg', 23657); 

SELECT * FROM survey;

-- 목록
SELECT survey_no, survey_head, survey_content,  rdate, survey_count
FROM survey 
ORDER BY survey_no ASC; 

-- 조회
SELECT survey_no, survey_head, survey_content, rdate, survey_count
FROM survey
WHERE survey_no=3;
