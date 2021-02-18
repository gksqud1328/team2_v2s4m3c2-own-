package dev.mvc.news_attachfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.news_attachfile.News_AttachfileProc")
public class News_AttachfileProc implements News_AttachfileProcInter {
  @Autowired
  private News_AttachfileDAOInter news_attachfileDAO;
  
  public News_AttachfileProc() {
    System.out.println("--> News_AttachfileProc created.");
  } 
  
  @Override
  public int create(News_AttachfileVO news_AttachfileVO) {
    int cnt = this.news_attachfileDAO.create(news_AttachfileVO);
    return cnt;
  }
  
  @Override
  public List<News_AttachfileVO> list() {
    List<News_AttachfileVO> list = this.news_attachfileDAO.list();
    return list;
  }
  
  @Override
  public News_AttachfileVO read(int news_attachfile_no) {
    News_AttachfileVO news_AttachfileVO = this.news_attachfileDAO.read(news_attachfile_no);
    
    return news_AttachfileVO;
  }

   /**
   * 첨부 파일 목록, 파일 용량 단위 출력
   */
  @Override
  public List<News_AttachfileVO> list_by_news_no(int news_no) {
    List<News_AttachfileVO> list = news_attachfileDAO.list_by_news_no(news_no);
    for (News_AttachfileVO news_AttachfileVO : list) {
      long news_attachfile_size = news_AttachfileVO.getNews_attachfile_size();
      String rlabel = Tool.unit(news_attachfile_size);  // 파일 단위 적용
      news_AttachfileVO.setRlabel(rlabel);
    }
    return list;
  }
  
  /**
   * 삭제
   */
  @Override
  public int delete(int news_attachfile_no) {
    int cnt = this.news_attachfileDAO.delete(news_attachfile_no);
    return cnt;
    
  }
  
  /**
   * FK별 전체 삭제
   */
  @Override
  public int delete_by_news_no(int news_no) {
    int cnt = this.news_attachfileDAO.delete_by_news_no(news_no);
    return cnt;
  }
  
  /**
   * FK별 갯수
   */
  @Override
  public int count_by_news_no(int news_no) {
    int cnt = this.news_attachfileDAO.count_by_news_no(news_no);
    return cnt;
  }

}
