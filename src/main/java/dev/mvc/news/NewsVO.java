package dev.mvc.news;

import org.springframework.web.multipart.MultipartFile;

//CREATE TABLE news(
//    news_no        NUMBER(10)   NOT NULL             PRIMARY KEY,
//    newsgrp_no   NUMBER(10)   NOT NULL,
//    news_head      CLOB         DEFAULT '����'       NOT NULL,
//    news_content   CLOB         DEFAULT '����'       NOT NULL,
//    news_comment   CLOB         DEFAULT '���'       NOT NULL,
//    news_count     NUMBER(10)   DEFAULT 0            NOT NULL,
//    news_mode      CLOB         DEFAULT'Y'           NOT NULL,
//    news_passwd    VARCHAR(15)                       NOT NULL,
//    file1          VARCHAR(100) NULL,
//    thumb1         VARCHAR(100) NULL,
//    size1          NUMBER(10)   DEFAULT 0 NULL,  
//    news_date      DATE         NOT NULL,
//    FOREIGN KEY(newsgrp_no) REFERENCES newsgrp(newsgrp_no)
//);

public class NewsVO {
  
  private int news_no;
  private int newsgrp_no;
  private String news_head ="";
  private String news_content ="";
  private String news_comment ="";
  private int news_count;
  private String news_date="";
  private String news_passwd="";
  private String file1="";
  private String thumb1="";
  private long size1;
  /** 
  �̹��� MultipartFile 
  <input type='file' class="form-control" name='file1MF' id='file1MF' 
                   value='' placeholder="���� ����" multiple="multiple">
  */
  private MultipartFile file1MF;
  
  public String getNews_passwd() {
    return news_passwd;
  }
  public void setNews_passwd(String news_passwd) {
    this.news_passwd = news_passwd;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public int getNews_no() {
    return news_no;
  }
  public void setNews_no(int news_no) {
    this.news_no = news_no;
  }
  
  public int getNewsgrp_no() {
    return newsgrp_no;
  }
  public void setNewsgrp_no(int newsgrp_no) {
    this.newsgrp_no = newsgrp_no;
  }
  
  public String getNews_head() {
    return news_head;
  }
  public void setNews_head(String news_head) {
    this.news_head = news_head;
  }
  public String getNews_content() {
    return news_content;
  }
  public void setNews_content(String news_content) {
    this.news_content = news_content;
  }
  public String getNews_comment() {
    return news_comment;
  }
  public void setNews_comment(String news_comment) {
    this.news_comment = news_comment;
  }
  public int getNews_count() {
    return news_count;
  }
  public void setNews_count(int news_count) {
    this.news_count = news_count;
  }
 
  public String getNews_date() {
    return news_date;
  }
  public void setNews_date(String news_date) {
    this.news_date = news_date;
  }
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getThumb1() {
    return thumb1;
  }
  public void setThumb1(String thumb1) {
    this.thumb1 = thumb1;
  }
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
   
}
