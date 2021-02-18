package dev.mvc.survey;

import org.springframework.web.multipart.MultipartFile;

//CREATE TABLE survey(
//survey_no          NUMBER(10)     NOT NULL               PRIMARY KEY,
//survey_head       VARCHAR(100)  DEFAULT   '��������'   NOT NULL,
//rdate                DATE              NOT NULL,
//survey_count       NUMBER(10)     DEFAULT     0          NOT NULL,
//survey_passwd     VARCHAR2(15)  DEFAULT    '1234'      NOT NULL
//);

public class SurveyVO {
  
 private int survey_no;
 private String survey_head="";
 private String rdate="";
 private int survey_count;
 private String survey_passwd="";

  public int getSurvey_no() {
    return survey_no;
  }
  public void setSurvey_no(int survey_no) {
    this.survey_no = survey_no;
  }
  public String getSurvey_head() {
    return survey_head;
  }
  public void setSurvey_head(String survey_head) {
    this.survey_head = survey_head;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getSurvey_count() {
    return survey_count;
  }
  public void setSurvey_count(int survey_count) {
    this.survey_count = survey_count;
  }
  public String getSurvey_passwd() {
    return survey_passwd;
  }
  public void setSurvey_passwd(String survey_passwd) {
    this.survey_passwd = survey_passwd;
  }

} 
 
