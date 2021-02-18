package dev.mvc.notice;

//DROP TABLE notice;
//CREATE TABLE notice(
//    noticeno           NUMBER(10)        NOT NULL           PRIMARY KEY,
//    seqno              NUMBER(10)    DEFAULT 0           NOT NULL,
//    head               CLOB              DEFAULT '��������'  NOT NULL,
//    content            CLOB              DEFAULT '��������'  NOT NULL,
//    count              NUMBER(10)        DEFAULT 0          NOT NULL ,
//    rdate              DATE              NOT NULL,  
//    visible            CHAR(1)           DEFAULT 'Y'        NOT NULL ,
//    passwd             VARCHAR2(15)      DEFAULT '1234'     NOT NULL
//);
//
//COMMENT ON TABLE notice is '��������';
//COMMENT ON COLUMN notice.noticeno is '��Ϲ�ȣ';
//COMMENT ON COLUMN notice.seqno is '������ȣ';
//COMMENT ON COLUMN notice.head is '����';
//COMMENT ON COLUMN notice.content is '����';
//COMMENT ON COLUMN notice.count is '��ȸ��';
//COMMENT ON COLUMN notice.rdate is '�����';
//COMMENT ON COLUMN notice.visible is '��¸��';
//COMMENT ON COLUMN notice.passwd is '�н�����';
  
public class NoticeVO{
  
  private int noticeno;
  private int seqno;
  private String head ="";
  private String content ="";
  private String rdate ="";
  private int count;
  private String visible ="";
  private String passwd="";
  
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public int getNoticeno() {
    return noticeno;
  }
  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getHead() {
    return head;
  }
  public void setHead(String head) {
    this.head = head;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public String getVisible() {
    return visible;
  }
  public void setVisible(String visible) {
    this.visible = visible;
  }
  
}
