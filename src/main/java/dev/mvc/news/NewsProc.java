package dev.mvc.news;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.news.NewsProc")
public class NewsProc implements NewsProcInter {
  @Autowired
  private NewsDAOInter newsDAO;
   
  public NewsProc() {
    System.out.println("--> NewsProc created.");
  }
  
  @Override 
  public int create(NewsVO newsVO) {
    int cnt = this.newsDAO.create(newsVO);
    return cnt;
  }
  
  @Override
  public List<NewsVO> list_all() {
    List<NewsVO> list = this.newsDAO.list_all();
    return list; 
  } 
  
  /**
   * 목록형
   */
  @Override
  public List<NewsVO> list(int newsgrp_no) {
    List<NewsVO> list = this.newsDAO.list(newsgrp_no);
    return list;
  }
  
  /**
   * 갤러리형
   */
  @Override
  public List<NewsVO> list_by_newsgrp_no(int newsgrp_no) {
    List<NewsVO> list = this.newsDAO.list_by_newsgrp_no(newsgrp_no);
    return list;
  }
  
  /**
   * 조회
   */
  @Override
  public NewsVO read(int news_no) {
    NewsVO newsVO = this.newsDAO.read(news_no);
    
    String news_head = newsVO.getNews_head();
    String news_content = newsVO.getNews_content();
    
    news_head = Tool.convertChar(news_head);  // 특수 문자 처리
    news_content = Tool.convertChar(news_content); // Ckeditor 사용시 사용하지 말 것.
    
    newsVO.setNews_head(news_head);
    newsVO.setNews_content(news_content);  // Ckeditor 사용시 사용하지 말 것.
    
//    long size1 = newsVO.getSize1();
    // contentsVO.setSize1_label(Tool.unit(size1));
    
    return newsVO;
  }
  
  @Override
  public NewsVO read_update(int news_no) {
    NewsVO newsVO = this.newsDAO.read(news_no);
    return newsVO; 
  } 

  @Override
  public int update(NewsVO newsVO) {
    int cnt = this.newsDAO.update(newsVO);
    return cnt;
  }
  
  @Override
  public int delete(int news_no) {
    int cnt = this.newsDAO.delete(news_no);
    return cnt;
  }
  
  @Override
  public int passwd_check(HashMap<String, Object> hashMap) {
    int passwd_cnt = this.newsDAO.passwd_check(hashMap);
    return passwd_cnt;
  } 
  
  @Override 
  public int img_create(NewsVO newsVO) {
    int cnt = this.newsDAO.update_img(newsVO);  
    return cnt;
  }
  
  @Override 
  public int img_update(NewsVO newsVO) {
    int cnt = this.newsDAO.update_img(newsVO);  
    return cnt;
  }
  
  @Override 
  public int img_delete(NewsVO newsVO) {
    int cnt = this.newsDAO.update_img(newsVO);  
    return cnt;
  }

  public int update_count(int news_no) {
    int cnt = this.newsDAO.update_count(news_no);
    return cnt;
  }
  
}