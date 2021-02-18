package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

public interface SurveyDAOInter{
  
  /**
   * ���
   * @param SurveyVO
   * @return
   */
  public int create(SurveyVO surveyVO);

  /**
   * ���
   * @param newsVO
   * @return
   */
  public List<SurveyVO> list(); 
  
  /**
   * ��ȸ, ���� ��, ���� ��
   * @param survey_no
   * @return
   */
  public SurveyVO read(int survey_no);

  /**
   * ���� ó��
   * <xmp>
   *   <update id="update" parameterType="SurveyVO"> 
   * </xmp>
   * @param SurveyeVO
   * @return ó���� ���ڵ� ����
   */
  public int update(SurveyVO surveyVO);
  
  /**
   * ����
   * @param surveyVO
   * @return
   */
  public int delete(int survey_no);
  
  /**
   * �н����� �˻�
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap hashMap);
  
  /**
   * ��ȸ�� ����
   * @param survey_no
   * @return
   */
  public int update_count(int survey_no);
  
}