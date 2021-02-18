/**********************************/
/* Table Name: �������� */
/**********************************/

DROP TABLE notice;
CREATE TABLE notice(
    noticeno           NUMBER(10)        NOT NULL           PRIMARY KEY,
    seqno              NUMBER(10)		 DEFAULT 0		       NOT NULL,
    head               CLOB              DEFAULT '��������'  NOT NULL,
    content            CLOB              DEFAULT '��������'  NOT NULL,
    count              NUMBER(10)        DEFAULT 0          NOT NULL ,
    rdate              DATE              NOT NULL,  
    visible            CHAR(1)           DEFAULT 'Y'        NOT NULL ,
    passwd             VARCHAR2(15)      DEFAULT '1234'     NOT NULL
);

COMMENT ON TABLE notice is '��������';
COMMENT ON COLUMN notice.noticeno is '��Ϲ�ȣ';
COMMENT ON COLUMN notice.seqno is '������ȣ';
COMMENT ON COLUMN notice.head is '����';
COMMENT ON COLUMN notice.content is '����';
COMMENT ON COLUMN notice.count is '��ȸ��';
COMMENT ON COLUMN notice.rdate is '�����';
COMMENT ON COLUMN notice.visible is '��¸��';
COMMENT ON COLUMN notice.passwd is '�н�����';

DROP SEQUENCE notice_seq;
-- create ��� / notice �� ��� ��ȣ
CREATE SEQUENCE notice_seq
  START WITH 1        -- ���� ��ȣ
  INCREMENT BY 1      -- ������
  MAXVALUE 9999999999 -- �ִ밪: 9999999 --> NUMBER(7) ����
  CACHE 2             -- 2���� �޸𸮿����� ���
  NOCYCLE;            -- �ٽ� 1���� �����Ǵ� ���� ����
  
INSERT INTO notice(noticeno, seqno ,head, content, passwd, rdate)
VALUES(notice_seq.nextval, 1, '��������1','������1','1234', sysdate);

INSERT INTO notice(noticeno, seqno, head, content, passwd, rdate)
VALUES(notice_seq.nextval,  2, '��������2','������2','1234', sysdate);

INSERT INTO notice(noticeno ,seqno ,head, content, passwd, rdate)
VALUES(notice_seq.nextval,  3, '��������3','������3','1234', sysdate);

SELECT*FROM notice;

--list ���
SELECT noticeno, seqno, head, content, count, visible,  rdate
FROM notice
ORDER BY noticeno ASC;
 NOTICENO    HEAD                  CONTENT       COUNT       VISIBLE      RDATE              
--------------- --------------------- -----------------   ------------   -----------     -------------------------
         1        ��������1              ������1             0               Y               2020-10-30 04:35:36
         2        ��������2              ������2             0               Y               2020-10-30 04:35:39
         3        ��������3              ������3             0               Y               2020-10-30 04:35:42

COMMIT;

--read_update ��ȸ, ���� ��, ���� ��
SELECT noticeno, seqno, head, content, rdate, count
FROM notice
WHERE noticeno=2;
 NOTICENO   HEAD                           CONTENT                        RDATE                         COUNT                                                                           
-------------- ----------------------------    ---------------------------     --------------------------    --------------
         2        ��������2                      ������2                           2020-10-30 04:35:39         0                                                                               

--update ����
UPDATE notice
SET head='����ȳ�2', content='�ѵ�', visible='Y' , noticeno=2
WHERE noticeno=3;

COMMIT;
NOTICENO      HEAD        CONTENT            RDATE        COUNT     visible
---------- ------------- ---------------   -----------   ---------  ---------
         1 ����ȳ�       ����ʼ�����....     20/10/27           0    N


--delete ����
DELETE FROM notice
WHERE noticeno=3;

SELECT*FROM notice;
NOTICENO HEAD            CONTENT         RDATE           COUNT        visible
---------- ----------- ---------------   --------------- -------- ------------
         1 ����ȳ�     ����ʼ�����....    20/10/27          0       N
         2 ��������2     ������2           20/10/27          0       Y
        
-- �н����� �˻�
SELECT COUNT(*) as passwd_cnt
FROM notice
WHERE noticeno=1 AND passwd='1234';

SELECT noticeno, passwd
FROM notice 
ORDER BY noticeno DESC;

COMMIT;

-- ��� ���������� ��ü ���
SELECT noticeno, head, content, rdate, count, visible
FROM notice
ORDER BY noticeno ASC;
 
-- ��� ���� ����, 10 �� 1
UPDATE notice
SET seqno = seqno - 1
WHERE noticeno=2;
 
-- ��¼��� ����, 1 �� 10
UPDATE notice
SET seqno = seqno + 1
WHERE noticeno=2;

SELECT * FROM notice;

commit;
-- ��� ����� ����
UPDATE notice
SET visible='Y'
WHERE noticeno=1;

UPDATE notice
SET visible='N'
WHERE noticeno=1;

commit;

-- read�� ��ȸ�� ����
UPDATE notice
SET count = count + 1
WHERE noticeno=1;

commit;

SELECT * FROM notice;