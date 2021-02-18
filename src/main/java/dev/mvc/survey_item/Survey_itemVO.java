package dev.mvc.survey_item;

//  CREATE TABLE survey_item(
//      item_no                         NUMBER(10)     NOT NULL    PRIMARY KEY,
//      survey_no                       NUMBER(10)     NOT NULL,
//      item                              VARCHAR2(200)    NOT NULL,
//      item_count                     NUMBER(7) DEFAULT 0  NOT NULL,
//    FOREIGN KEY (survey_no) REFERENCES survey (survey_no)
//  );
public class Survey_itemVO {  
    private int item_no;
    private int survey_no;
    private String item;
    private int item_count;
    
    public int getItem_no() {
      return item_no;
    }
    public void setItem_no(int item_no) {
      this.item_no = item_no;
    }
    public int getSurvey_no() {
      return survey_no;
    }
    public void setSurvey_no(int survey_no) {
      this.survey_no = survey_no;
    }
    public String getItem() {
      return item;
    }
    public void setItem(String item) {
      this.item = item;
    }
    public int getItem_count() {
      return item_count;
    }
    public void setItem_count(int item_count) {
      this.item_count = item_count;
    }

}

