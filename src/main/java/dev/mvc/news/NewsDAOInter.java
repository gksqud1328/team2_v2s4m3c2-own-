package dev.mvc.news;

import java.util.HashMap;
import java.util.List;

public interface NewsDAOInter {

  /**
   * 등록
   * @param newsVO
   * @return 
   */
  public int create(NewsVO newsVO);
   
  /**
   * 모든 뉴스 그룹의 등록된 글목록
   * @return
   */
  public List<NewsVO> list_all();
  
  /**
   * 특정 뉴스 그룹의 등록된 글목록(목록형)
   * @return
   */
  public List<NewsVO> list(int newsgrp_no);
  
  /**
   * 특정 뉴스 그룹의 등록된 글목록(그리드형)
   * @return
   */
  public List<NewsVO> list_by_newsgrp_no(int newsgrp_no);
 
  /**
   * 조회
   * @param news_no
   * @return
   */
  public NewsVO read(int news_no);
  
  /**
   * 수정용 조회
   * @param news_no
   * @return
   */
  public NewsVO read_update(int news_no);
  
  /**
   * 수정 처리
   * @param newsVO
   * @return
   */
  public int update(NewsVO newsVO);
  
  /**
   * 삭제
   * @param newsVO
   * @return
   */
  public int delete(int news_no);
  
  /**
   * 패스워드 검사
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap<String, Object>hashMap);
  
  /**
   * 이미지 변경
   * @param newsVO
   * @return
   */
  public int update_img(NewsVO newsVO);

  /**
   * 조회수 증가
   * <xmp>
   * <update id="update_count" parameterType="NoticeVO">
   * </xmp>
   * @param count
   * @return 처리된 레코드 갯수
   */
  public int update_count(int news_no);
}
 