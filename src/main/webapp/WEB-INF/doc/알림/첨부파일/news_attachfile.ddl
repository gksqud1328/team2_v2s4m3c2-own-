/**********************************/
/* Table Name: 전통주 뉴스 */
/**********************************/
CREATE TABLE NEWS(
		NEWS_NO                       		NUMBER(10)		 NOT NULL,
		NEWS_HEAD                     		CLOB(4000)		 NOT NULL,
		NEWS_CONTENT                  		CLOB(4000)		 NOT NULL,
		NEWS_COMMENT                  		CLOB(4000)		 NOT NULL,
		NEWS_COUNT                    		NUMBER(10)		 NOT NULL,
		NEWS_MODE                     		CLOB(4000)		 NOT NULL,
		NEWS_LIKE                     		NUMBER(10)		 NOT NULL,
		NEWS_PASSWD                   		VARCHAR2(15)		 NOT NULL,
		FILE1                         		VARCHAR2(100)		 NULL ,
		THUMB1                        		VARCHAR2(100)		 NULL ,
		SIZE1                         		NUMBER(10)		 NULL ,
		NEWS_DATE                     		DATE		 NOT NULL
);

COMMENT ON TABLE NEWS is '전통주 뉴스';
COMMENT ON COLUMN NEWS.NEWS_NO is '뉴스 번호';
COMMENT ON COLUMN NEWS.NEWS_HEAD is '제목';
COMMENT ON COLUMN NEWS.NEWS_CONTENT is '내용';
COMMENT ON COLUMN NEWS.NEWS_COMMENT is '댓글';
COMMENT ON COLUMN NEWS.NEWS_COUNT is '조회수';
COMMENT ON COLUMN NEWS.NEWS_MODE is '조회모드';
COMMENT ON COLUMN NEWS.NEWS_LIKE is '추천수';
COMMENT ON COLUMN NEWS.NEWS_PASSWD is '패스워드';
COMMENT ON COLUMN NEWS.FILE1 is '메인 이미지';
COMMENT ON COLUMN NEWS.THUMB1 is '메인 이미지 Preview';
COMMENT ON COLUMN NEWS.SIZE1 is '메인 이미지 크기';
COMMENT ON COLUMN NEWS.NEWS_DATE is '등록일';


DROP TABLE news_attachfile;

/**********************************/
/* Table Name: 리뷰 첨부파일 */
/**********************************/
CREATE TABLE news_attachfile(
    news_attachfile_no              NUMBER(10)     NOT NULL    PRIMARY KEY,
    news_no                         NUMBER(10)     NULL ,
    news_attachfile_rname           VARCHAR2(100)  NOT NULL,
    news_attachfile_upname        VARCHAR2(100)  NOT NULL,
    news_attachfile_thumb         VARCHAR2(100)  NULL ,
    news_attachfile_size            NUMBER(10)  DEFAULT 0  NOT NULL,
    news_attachfile_date            DATE     NOT NULL,
  FOREIGN KEY (news_no) REFERENCES news (news_no)
);

COMMENT ON TABLE news_attachfile is '리뷰 첨부파일';
COMMENT ON COLUMN  news_attachfile.news_attachfile_no is '첨부파일 번호';
COMMENT ON COLUMN  news_attachfile.news_no is '리뷰 번호';
COMMENT ON COLUMN  news_attachfile.news_attachfile_rname is '원본 파일';
COMMENT ON COLUMN  news_attachfile.news_attachfile_upname is '업로드 파일';
COMMENT ON COLUMN  news_attachfile.news_attachfile_thumb is '섬네일 파일';
COMMENT ON COLUMN  news_attachfile.news_attachfile_size is '파일 크기';
COMMENT ON COLUMN  news_attachfile.news_attachfile_date is '등록일';

DROP SEQUENCE news_attachfile_seq;
CREATE SEQUENCE news_attachfile_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

-- 1) 등록
INSERT INTO news_attachfile(news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date)
VALUES(news_attachfile_seq.nextval, 1, 'samyang.jpg', 'samyang_1.jpg', 'samyang_t.jpg', 1000, sysdate);

INSERT INTO news_attachfile(news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date)
VALUES(news_attachfile_seq.nextval, 2, 'samyang.jpg', 'samyang_2.jpg', 'samyang_t.jpg', 2000, sysdate);

INSERT INTO news_attachfile(news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date)
VALUES(news_attachfile_seq.nextval, 3, 'samyang.jpg', 'samyang_3.jpg', 'samyang_t.jpg', 3000, sysdate);

-- 2) 전체 목록( news_no 기준 내림 차순, news_attachfile_no 기준 오르차순)
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb,news_attachfile_size, news_attachfile_date
FROM news_attachfile
ORDER BY news_no DESC,  news_attachfile_no ASC;

-- 3) PK(news_attachfile_no) 기준 하나의 레코드 조회
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date
FROM news_attachfile
WHERE news_attachfile_no = 1;

-- 4) FK 기준 news_no 동일한 레코드 조회, fname 오름 차순
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date
FROM news_attachfile
WHERE news_no = 1
ORDER BY news_attachfile_rname ASC;

-- 5) 하나의 파일 삭제
DELETE FROM news_attachfile
WHERE news_attachfile_no = 9;

-- 6) 부모키별 갯수 산출
SELECT COUNT(*) as cnt
FROM news_attachfile
WHERE news_no=2;

-- 6) FK 부모키별 레코드 삭제
DELETE FROM news_attachfile
WHERE news_no=1; 

-- 7) news & news_attachfile join
    SELECT r.news_head, 
               a.news_attachfile_no, a.news_no, a.news_attachfile_rname, a.news_attachfile_upname, a.news_attachfile_thumb, a.news_attachfile_size, a.news_attachfile_date
    FROM news r, news_attachfile a
    WHERE r.news_no = a.news_no
    ORDER BY r.news_no DESC,  a.news_attachfile_no ASC;
   
-- 8) 조회
SELECT news_attachfile_no, news_no, news_attachfile_rname, news_attachfile_upname, news_attachfile_thumb, news_attachfile_size, news_attachfile_date
FROM news_attachfile
WHERE news_attachfile_no=3;

COMMIT;
