package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeDAOInter {

  /**
   * 등록
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);

  /**
   * 목록
   * @param noticeVO
   * @return
   */
  public List<NoticeVO> list();

  /**
   * 조회, 수정 폼, 삭제 폼
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);

  /**
   * 수정 처리
   * <xmp>
   *   <update id="update" parameterType="NoticeVO"> 
   * </xmp>
   * @param noticeVO
   * @return 처리된 레코드 갯수
   */
  public int update(NoticeVO noticeVO);
   
  /**
   * 삭제
   * <xmp>
   *   <update id="update" parameterType="NoticeVO"> 
   * </xmp>
   * @param noticeVO
   * @return 처리된 레코드 갯수
   */
  public int delete(int noticeno);
    
  /**
   * 패스워드 검사
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap hashMap);
    
  /**
   * 출력 순서 상향
   * <xmp>
   * <update id="update_noticeno_up" parameterType="int">
   * </xmp>
   * @param noticeno
   * @return 처리된 레코드 갯수
   */
  public int update_noticeno_up(int noticeno);
  
  /**
   * 출력 순서 하향
   * <xmp>
   * <update id="update_noticeno_down" parameterType="int">
   * </xmp>
   * @param noticeno
   * @return 처리된 레코드 갯수
   */
  public int update_noticeno_down(int noticeno);
  
  /**
   * 출력 모드 변경
   * <xmp>
   * <update id="update_visible" parameterType="int">
   * </xmp>
   * @param noticeno
   * @return 처리된 레코드 갯수
   */
  public int update_visible(NoticeVO noticeVO);
  
  /**
   * 조회수 증가
   * <xmp>
   * <update id="update_count" parameterType="NoticeVO">
   * </xmp>
   * @param count
   * @return 처리된 레코드 갯수
   */
  public int update_count(int noticeno);
}
