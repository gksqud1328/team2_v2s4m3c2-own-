package dev.mvc.news_attachfile;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/*
  * news_attachfile_no             NUMBER(10)     NOT NULL    PRIMARY KEY,
    news_no                         NUMBER(10)     NULL ,
    news_attachfile_rname           VARCHAR2(100)    NOT NULL,
    news_attachfile_upname          VARCHAR2(100)    NOT NULL,
    news_attachfile_thumb           VARCHAR2(100)    NULL ,
    news_attachfile_size            NUMBER(10)     NOT NULL,
    news_attachfile_date            DATE     NOT NULL,
    FOREIGN KEY (news_no) REFERENCES news (news_no)
 */

public class News_AttachfileVO { 
  /** 첨부파일 번호(PK) */
  private int news_attachfile_no;  
  /** 리뷰 번호(FK) */
  private int news_no;  
  /** 원본 파일명 */
  private String news_attachfile_rname; 
  /** 업로드 파일명 */
  private String news_attachfile_upname; 
  /** 섬네일 이미지 */
  private String news_attachfile_thumb;  
  /** 파일 크기 */
  private long news_attachfile_size;  
  /** 등록일 */
  private String news_attachfile_date;  
  //private MultipartFile fnameMF;  // 하나의 파일 처리
  /** Form의 파일을 MultipartFile로 변환하여 List에 저장  */
  private List<MultipartFile> news_attachfile_rnamesMF;
  /** 파일 단위 출력 */
  private String rlabel;
  /////////////////////////////////////////////////////////////////////////////////////////
 public int getNews_attachfile_no() {
    return news_attachfile_no;
  }
  public void setNews_attachfile_no(int news_attachfile_no) {
    this.news_attachfile_no = news_attachfile_no;
  }
  public int getNews_no() {
    return news_no;
  }
  public void setNews_no(int news_no) {
    this.news_no = news_no;
  }
  public String getNews_attachfile_rname() {
    return news_attachfile_rname;
  }
  public void setNews_attachfile_rname(String news_attachfile_rname) {
    this.news_attachfile_rname = news_attachfile_rname;
  }
  public String getNews_attachfile_upname() {
    return news_attachfile_upname;
  }
  public void setNews_attachfile_upname(String news_attachfile_upname) {
    this.news_attachfile_upname = news_attachfile_upname;
  }
  public String getNews_attachfile_thumb() {
    return news_attachfile_thumb;
  }
  public void setNews_attachfile_thumb(String news_attachfile_thumb) {
    this.news_attachfile_thumb = news_attachfile_thumb;
  }
  public long getNews_attachfile_size() {
    return news_attachfile_size;
  }
  public void setNews_attachfile_size(long news_attachfile_size) {
    this.news_attachfile_size = news_attachfile_size;
  }
  public String getNews_attachfile_date() {
    return news_attachfile_date;
  }
  public void setNews_attachfile_date(String news_attachfile_date) {
    this.news_attachfile_date = news_attachfile_date;
  }
  public List<MultipartFile> getNews_attachfile_rnamesMF() {
    return news_attachfile_rnamesMF;
  }
  public void setNews_attachfile_rnamesMF(List<MultipartFile> news_attachfile_rnamesMF) {
    this.news_attachfile_rnamesMF = news_attachfile_rnamesMF;
  }
  public String getRlabel() {
    return rlabel;
  }
  public void setRlabel(String rlabel) {
    this.rlabel = rlabel;
  }   
  
}
