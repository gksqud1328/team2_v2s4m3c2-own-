/**********************************/
/* Table Name: ���� �׷� */
/**********************************/
DROP TABLE newsgrp;
CREATE TABLE newsgrp(
		newsgrp_no                     	NUMBER(10)		 NOT NULL		           PRIMARY KEY,
		newsgrp_head                     CLOB	             NOT NULL,
		newsgrp_seqno                    NUMBER(10)		 DEFAULT 0		       NOT NULL,
		newsgrp_date                     DATE		             NOT NULL
);

COMMENT ON TABLE newsgrp is '���� �׷�';
COMMENT ON COLUMN newsgrp.newsgrp_no is '���� �׷� ��ȣ';
COMMENT ON COLUMN newsgrp.newsgrp_head is '�̸�';
COMMENT ON COLUMN newsgrp.newsgrp_seqno is '��� ����';
COMMENT ON COLUMN newsgrp.newsgrp_date is '�׷� ������';

DROP SEQUENCE  newsgrp_seq;
CREATE SEQUENCE newsgrp_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����
  
-- insert
INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, 'Ź��', 1, sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, 'û��', 2,  sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '������', 3,  sysdate);

SELECT * FROM newsgrp;

COMMIT;

-- list
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date
FROM newsgrp
ORDER BY newsgrp_no ASC;

-- ��ȸ + ������
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date 
FROM newsgrp
WHERE newsgrp_no = 1;
         
-- ����
UPDATE newsgrp
SET newsgrp_head='����..', newsgrp_seqno = 2
WHERE newsgrp_no = 2;

commit;

-- ��ȸ + ������ + ������
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date 
FROM newsgrp
WHERE newsgrp_no = 3;
 
-- ����         
DELETE FROM newsgrp
WHERE newsgrp_no = 3;

DELETE FROM newsgrp
WHERE newsgrp_head='������';

commit;

select * from newsgrp;
 
-- ��� ���������� ��ü ���
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date
FROM newsgrp
ORDER BY newsgrp_seqno ASC;
 
-- ��� ���� ����, 10 �� 1
UPDATE newsgrp
SET newsgrp_seqno = newsgrp_seqno - 1
WHERE newsgrp_no=1;
 
-- ��¼��� ����, 1 �� 10
UPDATE newsgrp
SET newsgrp_seqno = newsgrp_seqno + 1
WHERE newsgrp_no=1;

select * from newsgrp;
commit;
         
         
         
         