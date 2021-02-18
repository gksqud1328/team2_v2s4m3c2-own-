package dev.mvc.news_attachfile;

import java.util.List;

public interface News_AttachfileProcInter {
  
  /**
   * 파일 등록  
   * @param News_AttachfileVO
   * @return
   */
  public int create(News_AttachfileVO news_AttachfileVO);
  
   /**
   * 전체 이미지 목록
   * @return
   */
  public List<News_AttachfileVO> list();
  
   /**
   * 조회
   * @param news_attachfile_no
   * @return
   */
  public News_AttachfileVO read(int news_attachfile_no);
  
   /**
   * news_no에 따른 파일 목록
   * @param news_no
   * @return
   */
  public List<News_AttachfileVO> list_by_news_no(int news_no);
  
   /**
   * 삭제
   * @param news_attachfile_no
   * @return
   */
  public int delete(int news_attachfile_no);
  
  /**
   * news_no별 삭제
   * @param news_no
   * @return
   */
  public int delete_by_news_no(int news_no);
  
   /**
   * news_no별 카운트
   * @param news_no
   * @return
   */
  public int count_by_news_no(int news_no);

  

}
