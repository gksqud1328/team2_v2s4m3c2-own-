DROP TABLE news_attachfile;

/**********************************/
/* Table Name: ���� ÷������ */
/**********************************/
CREATE TABLE news_attachfile(
		news_attachfile_no          		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		news_no                     		NUMBER(10)		 NULL ,
		news_attachfile_rname          VARCHAR2(100)  NOT NULL,
		news_attachfile_upname      	VARCHAR2(100)	 NOT NULL,
		news_attachfile_thumb       	VARCHAR2(100)	 NULL ,
		news_attachfile_size        		NUMBER(10)	DEFAULT 0	 NOT NULL,
		news_attachfile_date        		DATE		 NOT NULL,
  FOREIGN KEY (news_no) REFERENCES news (news_no)
);

COMMENT ON TABLE news_attachfile is '���� ÷������';
COMMENT ON COLUMN  news_attachfile.news_attachfile_no is '÷������ ��ȣ';
COMMENT ON COLUMN  news_attachfile.news_no is '���� ��ȣ';
COMMENT ON COLUMN  news_attachfile.news_attachfile_rname is '���� ����';
COMMENT ON COLUMN  news_attachfile.news_attachfile_upname is '���ε� ����';
COMMENT ON COLUMN  news_attachfile.news_attachfile_thumb is '������ ����';
COMMENT ON COLUMN  news_attachfile.news_attachfile_size is '���� ũ��';
COMMENT ON COLUMN  news_attachfile.news_attachfile_date is '�����';

DROP SEQUENCE news_attachfile_seq;
CREATE SEQUENCE news_attachfile_seq
  START WITH 1              -- ���� ��ȣ
  INCREMENT BY 1          -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2                       -- 2���� �޸𸮿����� ���
  NOCYCLE;                     -- �ٽ� 1���� �����Ǵ� ���� ����

-- 1) ���
INSERT INTO news_attachfile(news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date)
VALUES(news_attachfile_seq.nextval, 1, 'samyang.jpg', 'samyang_1.jpg', 'samyang_t.jpg', 1000, sysdate);

INSERT INTO news_attachfile(news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date)
VALUES(news_attachfile_seq.nextval, 2, 'samyang.jpg', 'samyang_2.jpg', 'samyang_t.jpg', 2000, sysdate);

INSERT INTO news_attachfile(news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date)
VALUES(news_attachfile_seq.nextval, 3, 'samyang.jpg', 'samyang_3.jpg', 'samyang_t.jpg', 3000, sysdate);

-- 2) ��ü ���( news_no ���� ���� ����, news_attachfile_no ���� ��������)
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb,news_attachfile_size, news_attachfile_date
FROM news_attachfile
ORDER BY news_no DESC,  news_attachfile_no ASC;

-- 3) PK(news_attachfile_no) ���� �ϳ��� ���ڵ� ��ȸ
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date
FROM news_attachfile
WHERE news_attachfile_no = 1;

-- 4) FK ���� news_no ������ ���ڵ� ��ȸ, fname ���� ����
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date
FROM news_attachfile
WHERE news_no = 1
ORDER BY news_attachfile_rname ASC;

-- 5) �ϳ��� ���� ����
DELETE FROM news_attachfile
WHERE news_attachfile_no = 9;

-- 6) �θ�Ű�� ���� ����
SELECT COUNT(*) as cnt
FROM news_attachfile
WHERE news_no=2;

-- 6) FK �θ�Ű�� ���ڵ� ����
DELETE FROM news_attachfile
WHERE news_no=1; 

-- 7) news & news_attachfile join
    SELECT r.news_head, 
               a.news_attachfile_no, a.news_no, a.news_attachfile_rname, a.news_attachfile_upname, a.news_attachfile_thumb, a.news_attachfile_size, a.news_attachfile_date
    FROM news r, news_attachfile a
    WHERE r.news_no = a.news_no
    ORDER BY r.news_no DESC,  a.news_attachfile_no ASC;
   
-- 8) ��ȸ
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date
FROM news_attachfile
WHERE news_attachfile_no=3;

COMMIT;
