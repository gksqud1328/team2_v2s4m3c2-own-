package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

public interface SurveyDAOInter{
  
  /**
   * 등록
   * @param SurveyVO
   * @return
   */
  public int create(SurveyVO surveyVO);

  /**
   * 목록
   * @param newsVO
   * @return
   */
  public List<SurveyVO> list(); 
  
  /**
   * 조회, 수정 폼, 삭제 폼
   * @param survey_no
   * @return
   */
  public SurveyVO read(int survey_no);

  /**
   * 수정 처리
   * <xmp>
   *   <update id="update" parameterType="SurveyVO"> 
   * </xmp>
   * @param SurveyeVO
   * @return 처리된 레코드 갯수
   */
  public int update(SurveyVO surveyVO);
  
  /**
   * 삭제
   * @param surveyVO
   * @return
   */
  public int delete(int survey_no);
  
  /**
   * 패스워드 검사
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap hashMap);
  
  /**
   * 조회수 증가
   * @param survey_no
   * @return
   */
  public int update_count(int survey_no);
  
}