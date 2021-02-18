package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyCont{
  
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc;
  
  public SurveyCont() {
    System.out.println("--> SurveyCont created.");
  }
  
  /**
   * 등록폼 http://localhost:9090/team2/survey/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/create"); 

    return mav;
  }
  
  /**
   * 등록처리 http://localhost:9090/team2/survey/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey/create.do", method = RequestMethod.POST)
  public ModelAndView create(SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/create_msg");

    int cnt = this.surveyProc.create(surveyVO);
    mav.addObject("cnt", cnt);

    return mav;
  }
  
  /**
   * 목록 http://localhost:9090/team2/survey/list.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/list");
    
    List<SurveyVO> list = this.surveyProc.list();
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * 조회http://localhost:9090/team2/survey/read.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey/read.do", method = RequestMethod.GET)
  public ModelAndView read(int survey_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/read"); 

    int cnt = this.surveyProc.update_count(survey_no);
    mav.addObject("cnt", cnt);
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);
    mav.addObject("surveyVO", surveyVO);

    List<SurveyVO> list = this.surveyProc.list();
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * 수정 폼 http://localhost:9090/team2/survey/update.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey/update.do", method = RequestMethod.GET)
  public ModelAndView update(int survey_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/update"); 

    SurveyVO surveyVO = this.surveyProc.read(survey_no);
    mav.addObject("surveyVO", surveyVO);

    List<SurveyVO> list = this.surveyProc.list();
    mav.addObject("list", list);

    return mav; // forward
  } 
  
  /**
   * 수정 처리
   * 
   * @param surveyVO
   * @return
   */
  @RequestMapping(value = "/survey/update.do", method = RequestMethod.POST)
  public ModelAndView update(SurveyVO surveyVO) {

    ModelAndView mav = new ModelAndView();

    int cnt = this.surveyProc.update(surveyVO);
    mav.addObject("cnt", cnt); // request에 저장
    
    mav.setViewName("/survey/update_msg");

    return mav;
  }

  /**
   * 삭제폼 http://localhost:9090/team2/survey/delete.do
   * @return
   */
  @RequestMapping(value = "/survey/read_delete.do", method = RequestMethod.GET)
  public ModelAndView survey_delete(int survey_no) {
    ModelAndView mav = new ModelAndView(); 

    SurveyVO surveyVO = this.surveyProc.read(survey_no);
    mav.addObject("surveyVO", surveyVO);
    
    mav.setViewName("/survey/read_delete");

    return mav; 
  }

  /**
   * 삭제 처리
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/survey/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, int survey_no, 
                              String survey_head, String survey_passwd) {
    ModelAndView mav = new ModelAndView();

    SurveyVO surveyVO = this.surveyProc.read(survey_no);
    survey_head = surveyVO.getSurvey_head();
    mav.addObject("survey_head", survey_head);
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("survey_no", survey_no);
    hashMap.put("survey_passwd", survey_passwd);
    
    
    int passwd_cnt=0;
    int cnt = 0; 
    
    passwd_cnt = this.surveyProc.passwd_check(hashMap);
    boolean sw= false;
     
    if(passwd_cnt == 1) {
      cnt = this.surveyProc.delete(survey_no);
    } 
    
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장

    mav.setViewName("/survey/delete_msg"); // /webapp/notice/delete_msg.jsp

    return mav;
  }
}