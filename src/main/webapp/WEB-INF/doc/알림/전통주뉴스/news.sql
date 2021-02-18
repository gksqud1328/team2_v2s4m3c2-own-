--  CASCADE option�� �̿��� �ڽ� ���̺��� ������ ���̺� ����, ���õ� ���������� ������.
DROP TABLE news CASCADE CONSTRAINTS;
/**********************************/
/* Table Name: ���� �׷� */
/**********************************/
DROP TABLE news; -- �ڽ����̺� ���� ����
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

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE news;
CREATE TABLE news(
    news_no        NUMBER(10)   NOT NULL             PRIMARY KEY,
    newsgrp_no   NUMBER(10)   NOT NULL,
    news_head      CLOB         DEFAULT '����'       NOT NULL,
    news_content   CLOB         DEFAULT '����'       NOT NULL,
    news_comment   CLOB         DEFAULT '���'       NOT NULL,
    news_count     NUMBER(10)   DEFAULT 0            NOT NULL,
    news_passwd    VARCHAR(15)                       NOT NULL,
    file1          VARCHAR(100) NULL,
    thumb1         VARCHAR(100) NULL,
    size1          NUMBER(10)   DEFAULT 0 NULL,  
    news_date      DATE         NOT NULL,
    FOREIGN KEY(newsgrp_no) REFERENCES newsgrp(newsgrp_no)
);

COMMENT ON TABLE news is '������ ����';
COMMENT ON COLUMN news.news_head is '����';
COMMENT ON COLUMN news.news_content is '����';
COMMENT ON COLUMN news.news_comment is '���';
COMMENT ON COLUMN news.news_count is '��ȸ��';
COMMENT ON COLUMN news.news_date is '�����';
COMMENT ON COLUMN news.news_passwd is '�н�����';
COMMENT ON COLUMN news.file1 is '���� �̹���';
COMMENT ON COLUMN news.thumb1 is '���� �̹��� Preview';
COMMENT ON COLUMN news.size1 is ' ���� �̹��� ũ��';


DROP SEQUENCE news_seq;
-- create ���
CREATE SEQUENCE news_seq
  START WITH 1        -- ���� ��ȣ
  INCREMENT BY 1      -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2             -- 2���� �޸𸮿����� ���
  NOCYCLE;            -- �ٽ� 1���� �����Ǵ� ���� ����
  
-- �� ���
-- newsgrp_no(�ѹ�)�� ������ ��ϵǾ� �־�� INSERT ����
-- �θ����̺� newsgrp �׷� �� ���
INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, 'Ź��', 1, sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, 'û��', 2,  sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '������', 3,  sysdate);

-- �ڽ����̺� news �� ���
INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 1, 'Ź�� ��1', 'Ź��..', 'Ź�ִ��..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 1, 'Ź�� ��2', 'Ź��2..', 'Ź�ִ��2..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 2, 'û�� ��', 'û��..', 'û�ִ��..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 2, 'û�� ��2', 'û��2..', 'û�ִ��2..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 3, '�����ֱ�', '������..', '�����ִ��..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

SELECT * FROM newsgrp ORDER BY newsgrp_no ASC;

SELECT news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1
FROM news 
ORDER BY news_no ASC;  

SELECT * FROM news;

COMMIT;

-- ���� sequence�� Ȯ��
SELECT news_seq.nextval FROM dual;
SELECT news_seq.currval FROM dual;

-- ��ü���
SELECT news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1
FROM news
ORDER BY news_no DESC;

-- news_no �� ���
SELECT news_no, newsgrp_no,  news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1
FROM news 
WHERE newsgrp_no = 1
ORDER BY news_no DESC;

-- list ���
SELECT news_no, news_head, news_content, news_count, news_date
FROM news 
ORDER BY news_no ASC; 

-- read ��ȸ
SELECT news_no, news_head, news_content, news_comment, news_count, news_date
FROM news
WHERE news_no=3;

--update ����
UPDATE news
SET news_head='������ ����', news_content='������������'
WHERE news_no=1;

SELECT * FROM news;

COMMIT;

--delete ����
DELETE news
WHERE news_no=8;

SELECT * FROM news;
COMMIT;

-- �н����� �˻�
SELECT COUNT(*) as passwd_cnt
FROM news
WHERE news_no=3 AND news_passwd='1234';

SELECT news_no, news_passwd
FROM news 
ORDER BY news_no DESC;

-- ÷�� ���� ����(���, ����, ����, ����)
UPDATE news
SET file1='file name', thumb1='thumb file name', size1=5000
WHERE news_no=10; 
  
SELECT news_no, newsgrp_no, file1, thumb1, size1
FROM news 
ORDER BY news_no ASC; 

COMMIT;

-- �˻� + ����¡ + �����̹���
SELECT news_no, newsgrp_no, news_head, news_content, news_recom, cnt, replycnt, rdate, word, ip,
          file1, thumb1, size1
FROM news
WHERE cateno=27 AND word LIKE '%��ǳ%'
ORDER BY contentsno DESC;