package dev.mvc.newsgrp;

//CREATE TABLE newsgrp(
//    newsgrp_no                      NUMBER(10)     NOT NULL    PRIMARY KEY,
//    newsgrp_head                     VARCHAR2(100)     NOT NULL,
//    newsgrp_seqno                    NUMBER(10)    DEFAULT 0     NOT NULL,
//    newsgrp_date                     DATE    NOT NULL
//);

public class NewsgrpVO {
  
  /** 리뷰 카테고리 그룹 번호 */
  private int newsgrp_no;
  
  /** 리뷰 카테고리 그룹 이름 */
  private String newsgrp_head="";
  
  /** 리뷰 카테고리 그룹 출력순서 */
  private int newsgrp_seqno;
  
  /** 리뷰 카테고리 그룹 생성일 */
  private String newsgrp_date="";

  public int getNewsgrp_no() {
    return newsgrp_no;
  }

  public void setNewsgrp_no(int newsgrp_no) {
    this.newsgrp_no = newsgrp_no;
  }

  public String getNewsgrp_head() {
    return newsgrp_head;
  }

  public void setNewsgrp_head(String newsgrp_head) {
    this.newsgrp_head = newsgrp_head;
  }

  public int getNewsgrp_seqno() {
    return newsgrp_seqno;
  }

  public void setNewsgrp_seqno(int newsgrp_seqno) {
    this.newsgrp_seqno = newsgrp_seqno;
  }

  public String getNewsgrp_date() {
    return newsgrp_date;
  }

  public void setNewsgrp_date(String newsgrp_date) {
    this.newsgrp_date = newsgrp_date;
  }

}
