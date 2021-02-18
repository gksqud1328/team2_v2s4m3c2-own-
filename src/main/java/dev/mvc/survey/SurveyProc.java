package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.survey.SurveyProc")
public class SurveyProc implements SurveyProcInter{
  @Autowired
  private SurveyDAOInter surveyDAO;
  
  public SurveyProc() {
    System.out.println("--> SurveyProc created.");
  }
  @Override
  public int create(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.create(surveyVO);
    return cnt;
  }

  @Override
  public List<SurveyVO> list() {
    List<SurveyVO> list = this.surveyDAO.list();
    return list; 
  } 
  
  @Override
  public SurveyVO read(int survey_no) {
    SurveyVO surveyVO = this.surveyDAO.read(survey_no);
    return surveyVO;
  }
  
  @Override
  public int update(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.update(surveyVO);
    return cnt;
  } 
  
  @Override
  public int delete(int survey_no) {
    int cnt = this.surveyDAO.delete(survey_no);
    return cnt;
  } 
  
  @Override
  public int passwd_check(HashMap hashMap) {
    int passwd_cnt = this.surveyDAO.passwd_check(hashMap);  
    return passwd_cnt;
  }
  
  public int update_count(int news_no) {
    int cnt = this.surveyDAO.update_count(news_no);
    return cnt;
  }
  
}
