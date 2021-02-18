/**********************************/
/* Table Name: survey */
/**********************************/
DROP TABLE survey;

CREATE TABLE survey(
survey_no             NUMBER(10)          NOT NULL                PRIMARY KEY,
survey_head          CLOB                    DEFAULT   '��������'   NOT NULL,
survey_content      CLOB                     DEFAULT   ' ����'       NOT NULL,
rdate                   DATE                    NOT NULL,
survey_count        NUMBER(10)          DEFAULT     0             NOT NULL,
survey_passwd      VARCHAR2(15)      DEFAULT    '1234'       NOT NULL,
file1                    VARCHAR2(100)     NULL,
thumb1               VARCHAR2(100)     NULL,
size1                    NUMBER(10)         DEFAULT  0 NULL
);

COMMENT ON TABLE survey is '��������';
COMMENT ON COLUMN survey.survey_no is '��Ϲ�ȣ';
COMMENT ON COLUMN survey.survey_head is '����';
COMMENT ON COLUMN survey.survey_content is '����';
COMMENT ON COLUMN survey.rdate is '�����';
COMMENT ON COLUMN survey.survey_count is '��ȸ��';
COMMENT ON COLUMN survey.survey_passwd is '��й�ȣ';
COMMENT ON COLUMN survey.file1 is '���� �̹���';
COMMENT ON COLUMN survey.thumb1 is '���� �̹��� Preview';
COMMENT ON COLUMN survey.size1 is '���� �̹��� ũ��';

DROP SEQUENCE survey_seq;
CREATE SEQUENCE survey_seq
  START WITH 1        -- ���� ��ȣ
  INCREMENT BY 1      -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2             -- 2���� �޸𸮿����� ���
  NOCYCLE;            -- �ٽ� 1���� �����Ǵ� ���� ����
  
INSERT INTO survey(survey_no, survey_head, survey_content, rdate, survey_count, survey_passwd, file1, thumb1, size1)
VALUES(survey_seq.nextval, '��������', '�����ٶ�', sysdate, 0, '1234', 'spring.jpg', 'spring_t.jpg', 23657); 

INSERT INTO survey(survey_no, survey_head, survey_content, rdate, survey_count, survey_passwd, file1, thumb1, size1)
VALUES(survey_seq.nextval, '��������2', 'abcd', sysdate, 0, '1234', 'spring.jpg', 'spring_t.jpg', 23657); 

INSERT INTO survey(survey_no, survey_head, survey_content, rdate, survey_count, survey_passwd, file1, thumb1, size1)
VALUES(survey_seq.nextval, '��������3', '����..', sysdate, 0, '1234', 'spring.jpg', 'spring_t.jpg', 23657); 

SELECT * FROM survey;

-- ���
SELECT survey_no, survey_head, survey_content,  rdate, survey_count
FROM survey 
ORDER BY survey_no ASC; 

-- ��ȸ
SELECT survey_no, survey_head, survey_content, rdate, survey_count
FROM survey
WHERE survey_no=3;
