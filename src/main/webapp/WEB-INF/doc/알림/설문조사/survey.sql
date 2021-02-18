/**********************************/
/* Table Name: survey */
/**********************************/
DROP TABLE survey;

CREATE TABLE survey(
survey_no             NUMBER(10)          NOT NULL                PRIMARY KEY,
survey_head          VARCHAR(100)      DEFAULT   '��������'   NOT NULL,
rdate                   DATE                    NOT NULL,
survey_count        NUMBER(10)          DEFAULT     0             NOT NULL,
survey_passwd      VARCHAR2(15)      DEFAULT    '1234'       NOT NULL
);

COMMENT ON TABLE survey is '��������';
COMMENT ON COLUMN survey.survey_no is '��Ϲ�ȣ';
COMMENT ON COLUMN survey.survey_head is '����';
COMMENT ON COLUMN survey.rdate is '�����';
COMMENT ON COLUMN survey.survey_count is '��ȸ��';
COMMENT ON COLUMN survey.survey_passwd is '��й�ȣ';

DROP SEQUENCE survey_seq;
CREATE SEQUENCE survey_seq
  START WITH 1        -- ���� ��ȣ
  INCREMENT BY 1      -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2             -- 2���� �޸𸮿����� ���
  NOCYCLE;            -- �ٽ� 1���� �����Ǵ� ���� ����
  
INSERT INTO survey(survey_no, survey_head, rdate, survey_count, survey_passwd)
VALUES(survey_seq.nextval, '��������',  sysdate, 0, '1234'); 

INSERT INTO survey(survey_no, survey_head, rdate, survey_count, survey_passwd)
VALUES(survey_seq.nextval, '��������2', sysdate, 0, '1234'); 

INSERT INTO survey(survey_no, survey_head,  rdate, survey_count, survey_passwd)
VALUES(survey_seq.nextval, '��������3',  sysdate, 0, '1234'); 

SELECT * FROM survey;

COMMIT;
-- ���
SELECT survey_no, survey_head,  rdate, survey_count
FROM survey 
ORDER BY survey_no ASC; 

-- ��ȸ
SELECT survey_no, survey_head, rdate, survey_count
FROM survey
WHERE survey_no=3;

-- ����
UPDATE survey
SET survey_head='��������4��'
WHERE survey_no=4;

-- read�� ��ȸ�� ����
UPDATE survey
SET survey_count = survey_count + 1
WHERE survey_no=1;

COMMIT;

/**********************************/
/* Table Name: survey_item     */ 
/**********************************/
-- �������� �׸� ���̺�

DROP TABLE survey_item; 
CREATE TABLE survey_item(
    survey_itemno                 NUMBER(10)     NOT NULL    PRIMARY KEY,
    survey_no                       NUMBER(10)     NOT NULL,
    item                              VARCHAR2(200)    NOT NULL,
    item_count                     NUMBER(7) DEFAULT 0  NOT NULL,
  FOREIGN KEY (survey_no) REFERENCES survey (survey_no)
);

COMMENT ON TABLE surveyitem is '���� ���� �׸�';
COMMENT ON COLUMN survey_item.survey_itemno is '���� ���� �׸� ��ȣ';
COMMENT ON COLUMN survey_item.surveyno is '���� ���� ��ȣ';
COMMENT ON COLUMN survey_item.item is '�׸�';
COMMENT ON COLUMN survey_item.item_count is '�׸� ���� ��';

DROP SEQUENCE item_seq;
CREATE SEQUENCE item_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(10) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
