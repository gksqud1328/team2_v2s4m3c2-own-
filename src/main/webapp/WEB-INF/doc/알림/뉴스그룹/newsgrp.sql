/**********************************/
/* Table Name: 뉴스 그룹 */
/**********************************/
DROP TABLE newsgrp;
CREATE TABLE newsgrp(
		newsgrp_no                     	NUMBER(10)		 NOT NULL		           PRIMARY KEY,
		newsgrp_head                     CLOB	             NOT NULL,
		newsgrp_seqno                    NUMBER(10)		 DEFAULT 0		       NOT NULL,
		newsgrp_date                     DATE		             NOT NULL
);

COMMENT ON TABLE newsgrp is '뉴스 그룹';
COMMENT ON COLUMN newsgrp.newsgrp_no is '뉴스 그룹 번호';
COMMENT ON COLUMN newsgrp.newsgrp_head is '이름';
COMMENT ON COLUMN newsgrp.newsgrp_seqno is '출력 순서';
COMMENT ON COLUMN newsgrp.newsgrp_date is '그룹 생성일';

DROP SEQUENCE  newsgrp_seq;
CREATE SEQUENCE newsgrp_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- insert
INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '탁주', 1, sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '청주', 2,  sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '증류주', 3,  sysdate);

SELECT * FROM newsgrp;

COMMIT;

-- list
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date
FROM newsgrp
ORDER BY newsgrp_no ASC;

-- 조회 + 수정폼
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date 
FROM newsgrp
WHERE newsgrp_no = 1;
         
-- 수정
UPDATE newsgrp
SET newsgrp_head='수정..', newsgrp_seqno = 2
WHERE newsgrp_no = 2;

commit;

-- 조회 + 수정폼 + 삭제폼
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date 
FROM newsgrp
WHERE newsgrp_no = 3;
 
-- 삭제         
DELETE FROM newsgrp
WHERE newsgrp_no = 3;

DELETE FROM newsgrp
WHERE newsgrp_head='수정본';

commit;

select * from newsgrp;
 
-- 출력 순서에따른 전체 목록
SELECT newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date
FROM newsgrp
ORDER BY newsgrp_seqno ASC;
 
-- 출력 순서 상향, 10 ▷ 1
UPDATE newsgrp
SET newsgrp_seqno = newsgrp_seqno - 1
WHERE newsgrp_no=1;
 
-- 출력순서 하향, 1 ▷ 10
UPDATE newsgrp
SET newsgrp_seqno = newsgrp_seqno + 1
WHERE newsgrp_no=1;

select * from newsgrp;
commit;
         
         
         
         