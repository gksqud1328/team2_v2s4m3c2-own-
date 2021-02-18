DROP TABLE news_attachfile;

/**********************************/
/* Table Name: 리뷰 첨부파일 */
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
