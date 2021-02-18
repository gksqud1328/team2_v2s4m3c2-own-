package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter {
  @Autowired
  private NoticeDAOInter noticeDAO;

  public NoticeProc() {
    System.out.println("--> NoticeProc created.");
  }
  
  @Override
  public int create(NoticeVO noticeVO) {
     int cnt=this.noticeDAO.create(noticeVO);
    return cnt;
  }
  
  @Override
  public List<NoticeVO> list() {
    List<NoticeVO> list = this.noticeDAO.list();
    return list;
  } 
  
  @Override
  public NoticeVO read(int noticeno) {
    NoticeVO noticeVO = this.noticeDAO.read(noticeno);
    return noticeVO;
  } 
  
  @Override
  public int update(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update(noticeVO);
    return cnt;
  } 
  
  @Override
  public int delete(int noticeno) {
    int cnt = this.noticeDAO.delete(noticeno);
    return cnt;
  }
  
  @Override
  public int passwd_check(HashMap hashMap) {
    int passwd_cnt = this.noticeDAO.passwd_check(hashMap);
    return passwd_cnt;
  }
  
  @Override
  public int update_noticeno_up(int noticeno) {
    int cnt = this.noticeDAO.update_noticeno_up(noticeno);
    return cnt;
  }
  
  @Override
  public int update_noticeno_down(int noticeno) {
    int cnt = this.noticeDAO.update_noticeno_down(noticeno);
    return cnt;
  }
  
  /**
   * Y -> N, N -> Y
   */
  @Override
  public int update_visible(NoticeVO noticeVO) {
    if (noticeVO.getVisible().equalsIgnoreCase("Y")) {
      noticeVO.setVisible("N");
    } else {
      noticeVO.setVisible("Y");
    }
    int cnt = this.noticeDAO.update_visible(noticeVO);
    
    return cnt;
  }
  
  @Override
  public int update_count(int noticeno) {
    int cnt = this.noticeDAO.update_count(noticeno);
    return cnt;
  }
  
}