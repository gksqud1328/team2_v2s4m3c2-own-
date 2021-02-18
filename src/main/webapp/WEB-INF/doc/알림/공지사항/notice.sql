/**********************************/
/* Table Name: 공지사항 */
/**********************************/

DROP TABLE notice;
CREATE TABLE notice(
    noticeno           NUMBER(10)        NOT NULL           PRIMARY KEY,
    seqno              NUMBER(10)		 DEFAULT 0		       NOT NULL,
    head               CLOB              DEFAULT '공지제목'  NOT NULL,
    content            CLOB              DEFAULT '공지내용'  NOT NULL,
    count              NUMBER(10)        DEFAULT 0          NOT NULL ,
    rdate              DATE              NOT NULL,  
    visible            CHAR(1)           DEFAULT 'Y'        NOT NULL ,
    passwd             VARCHAR2(15)      DEFAULT '1234'     NOT NULL
);

COMMENT ON TABLE notice is '공지사항';
COMMENT ON COLUMN notice.noticeno is '등록번호';
COMMENT ON COLUMN notice.seqno is '순서번호';
COMMENT ON COLUMN notice.head is '제목';
COMMENT ON COLUMN notice.content is '내용';
COMMENT ON COLUMN notice.count is '조회수';
COMMENT ON COLUMN notice.rdate is '등록일';
COMMENT ON COLUMN notice.visible is '출력모드';
COMMENT ON COLUMN notice.passwd is '패스워드';

DROP SEQUENCE notice_seq;
-- create 등록 / notice 글 등록 번호
CREATE SEQUENCE notice_seq
  START WITH 1        -- 시작 번호
  INCREMENT BY 1      -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2             -- 2번은 메모리에서만 계산
  NOCYCLE;            -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO notice(noticeno, seqno ,head, content, passwd, rdate)
VALUES(notice_seq.nextval, 1, '공지사항1','공지글1','1234', sysdate);

INSERT INTO notice(noticeno, seqno, head, content, passwd, rdate)
VALUES(notice_seq.nextval,  2, '공지사항2','공지글2','1234', sysdate);

INSERT INTO notice(noticeno ,seqno ,head, content, passwd, rdate)
VALUES(notice_seq.nextval,  3, '공지사항3','공지글3','1234', sysdate);

SELECT*FROM notice;

--list 목록
SELECT noticeno, seqno, head, content, count, visible,  rdate
FROM notice
ORDER BY noticeno ASC;
 NOTICENO    HEAD                  CONTENT       COUNT       VISIBLE      RDATE              
--------------- --------------------- -----------------   ------------   -----------     -------------------------
         1        공지사항1              공지글1             0               Y               2020-10-30 04:35:36
         2        공지사항2              공지글2             0               Y               2020-10-30 04:35:39
         3        공지사항3              공지글3             0               Y               2020-10-30 04:35:42

COMMIT;

--read_update 조회, 수정 폼, 삭제 폼
SELECT noticeno, seqno, head, content, rdate, count
FROM notice
WHERE noticeno=2;
 NOTICENO   HEAD                           CONTENT                        RDATE                         COUNT                                                                           
-------------- ----------------------------    ---------------------------     --------------------------    --------------
         2        공지사항2                      공지글2                           2020-10-30 04:35:39         0                                                                               

--update 수정
UPDATE notice
SET head='등업안내2', content='둘둘', visible='Y' , noticeno=2
WHERE noticeno=3;

COMMIT;
NOTICENO      HEAD        CONTENT            RDATE        COUNT     visible
---------- ------------- ---------------   -----------   ---------  ---------
         1 등업안내       등업필수조건....     20/10/27           0    N


--delete 삭제
DELETE FROM notice
WHERE noticeno=3;

SELECT*FROM notice;
NOTICENO HEAD            CONTENT         RDATE           COUNT        visible
---------- ----------- ---------------   --------------- -------- ------------
         1 등업안내     등업필수조건....    20/10/27          0       N
         2 공지사항2     공지글2           20/10/27          0       Y
        
-- 패스워드 검사
SELECT COUNT(*) as passwd_cnt
FROM notice
WHERE noticeno=1 AND passwd='1234';

SELECT noticeno, passwd
FROM notice 
ORDER BY noticeno DESC;

COMMIT;

-- 출력 순서에따른 전체 목록
SELECT noticeno, head, content, rdate, count, visible
FROM notice
ORDER BY noticeno ASC;
 
-- 출력 순서 상향, 10 ▷ 1
UPDATE notice
SET seqno = seqno - 1
WHERE noticeno=2;
 
-- 출력순서 하향, 1 ▷ 10
UPDATE notice
SET seqno = seqno + 1
WHERE noticeno=2;

SELECT * FROM notice;

commit;
-- 출력 모드의 변경
UPDATE notice
SET visible='Y'
WHERE noticeno=1;

UPDATE notice
SET visible='N'
WHERE noticeno=1;

commit;

-- read시 조회수 증가
UPDATE notice
SET count = count + 1
WHERE noticeno=1;

commit;

SELECT * FROM notice;