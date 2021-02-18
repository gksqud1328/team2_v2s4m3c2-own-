package dev.mvc.newsgrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.newsgrp.NewsgrpProc")
public class NewsgrpProc implements NewsgrpProcInter{
  @Autowired
  private NewsgrpDAOInter newsgrpDAO;
  
  public NewsgrpProc() {
    System.out.println("--> NewsgrpProc created.");
  }
  
  @Override
  public int create(NewsgrpVO newsgrpVO) {
    int cnt = this.newsgrpDAO.create(newsgrpVO);
    return cnt;
  }
  
  @Override
  public List<NewsgrpVO> list_newsgrp_no_asc() {
    List<NewsgrpVO> list = this.newsgrpDAO.list_newsgrp_no_asc();
    return list;
  }
  
  @Override
  public NewsgrpVO read(int newsgrp_no) {
    NewsgrpVO newsgrpVO = this.newsgrpDAO.read(newsgrp_no);
    
    return newsgrpVO;
  }
  
  @Override
  public int update(NewsgrpVO newsgrpVO) {
    int cnt = this.newsgrpDAO.update(newsgrpVO);
   
    return cnt;
  }
  
  @Override
  public int delete(int newsgrp_no) {
    int cnt = this.newsgrpDAO.delete(newsgrp_no);
    
    return cnt; 
  }
  
  @Override
  public List<NewsgrpVO> list_newsgrp_seqno_asc() {
    List<NewsgrpVO> list = this.newsgrpDAO.list_newsgrp_seqno_asc();
    
    return list; 
  }
  
  @Override
  public int update_newsgrp_seqno_up(int newsgrp_no) {
    int cnt = this.newsgrpDAO.update_newsgrp_seqno_up(newsgrp_no);
    
    return cnt;
  }
  
  @Override
  public int update_newsgrp_seqno_down(int newsgrp_no) {
    int cnt = this.newsgrpDAO.update_newsgrp_seqno_down(newsgrp_no);
    
    return cnt;
  }
  
  
}
