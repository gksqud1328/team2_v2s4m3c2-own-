--  CASCADE option을 이용한 자식 테이블을 무시한 테이블 삭제, 관련된 제약조건이 삭제됨.
DROP TABLE news CASCADE CONSTRAINTS;
/**********************************/
/* Table Name: 뉴스 그룹 */
/**********************************/
DROP TABLE news; -- 자식테이블 먼저 삭제
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

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE news;
CREATE TABLE news(
    news_no        NUMBER(10)   NOT NULL             PRIMARY KEY,
    newsgrp_no   NUMBER(10)   NOT NULL,
    news_head      CLOB         DEFAULT '제목'       NOT NULL,
    news_content   CLOB         DEFAULT '내용'       NOT NULL,
    news_comment   CLOB         DEFAULT '댓글'       NOT NULL,
    news_count     NUMBER(10)   DEFAULT 0            NOT NULL,
    news_passwd    VARCHAR(15)                       NOT NULL,
    file1          VARCHAR(100) NULL,
    thumb1         VARCHAR(100) NULL,
    size1          NUMBER(10)   DEFAULT 0 NULL,  
    news_date      DATE         NOT NULL,
    FOREIGN KEY(newsgrp_no) REFERENCES newsgrp(newsgrp_no)
);

COMMENT ON TABLE news is '전통주 뉴스';
COMMENT ON COLUMN news.news_head is '제목';
COMMENT ON COLUMN news.news_content is '내용';
COMMENT ON COLUMN news.news_comment is '댓글';
COMMENT ON COLUMN news.news_count is '조회수';
COMMENT ON COLUMN news.news_date is '등록일';
COMMENT ON COLUMN news.news_passwd is '패스워드';
COMMENT ON COLUMN news.file1 is '메인 이미지';
COMMENT ON COLUMN news.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN news.size1 is ' 메인 이미지 크기';


DROP SEQUENCE news_seq;
-- create 등록
CREATE SEQUENCE news_seq
  START WITH 1        -- 시작 번호
  INCREMENT BY 1      -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2             -- 2번은 메모리에서만 계산
  NOCYCLE;            -- 다시 1부터 생성되는 것을 방지
  
-- 글 등록
-- newsgrp_no(넘버)가 사전에 등록되어 있어야 INSERT 가능
-- 부모테이블 newsgrp 그룹 글 등록
INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '탁주', 1, sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '청주', 2,  sysdate);

INSERT INTO newsgrp(newsgrp_no, newsgrp_head, newsgrp_seqno, newsgrp_date)
VALUES(newsgrp_seq.nextval, '증류주', 3,  sysdate);

-- 자식테이블 news 글 등록
INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 1, '탁주 글1', '탁주..', '탁주댓글..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 1, '탁주 글2', '탁주2..', '탁주댓글2..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 2, '청주 글', '청주..', '청주댓글..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 2, '청주 글2', '청주2..', '청주댓글2..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

INSERT INTO news(news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1)
VALUES(news_seq.nextval, 3, '증류주글', '증류주..', '증류주댓글..', 0, sysdate, '1234', 'jipyeng.jpg', 'jipyeng.jpg', 23657); 

SELECT * FROM newsgrp ORDER BY newsgrp_no ASC;

SELECT news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1
FROM news 
ORDER BY news_no ASC;  

SELECT * FROM news;

COMMIT;

-- 현재 sequence의 확인
SELECT news_seq.nextval FROM dual;
SELECT news_seq.currval FROM dual;

-- 전체목록
SELECT news_no, newsgrp_no, news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1
FROM news
ORDER BY news_no DESC;

-- news_no 별 목록
SELECT news_no, newsgrp_no,  news_head, news_content, news_comment, news_count, news_date, news_passwd, file1, thumb1, size1
FROM news 
WHERE newsgrp_no = 1
ORDER BY news_no DESC;

-- list 목록
SELECT news_no, news_head, news_content, news_count, news_date
FROM news 
ORDER BY news_no ASC; 

-- read 조회
SELECT news_no, news_head, news_content, news_comment, news_count, news_date
FROM news
WHERE news_no=3;

--update 수정
UPDATE news
SET news_head='전통주 뉴스', news_content='전전통통주주'
WHERE news_no=1;

SELECT * FROM news;

COMMIT;

--delete 삭제
DELETE news
WHERE news_no=8;

SELECT * FROM news;
COMMIT;

-- 패스워드 검사
SELECT COUNT(*) as passwd_cnt
FROM news
WHERE news_no=3 AND news_passwd='1234';

SELECT news_no, news_passwd
FROM news 
ORDER BY news_no DESC;

-- 첨부 파일 변경(등록, 변경, 수정, 삭제)
UPDATE news
SET file1='file name', thumb1='thumb file name', size1=5000
WHERE news_no=10; 
  
SELECT news_no, newsgrp_no, file1, thumb1, size1
FROM news 
ORDER BY news_no ASC; 

COMMIT;

-- 검색 + 페이징 + 메인이미지
SELECT news_no, newsgrp_no, news_head, news_content, news_recom, cnt, replycnt, rdate, word, ip,
          file1, thumb1, size1
FROM news
WHERE cateno=27 AND word LIKE '%단풍%'
ORDER BY contentsno DESC;