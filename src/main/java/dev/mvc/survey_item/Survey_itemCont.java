package dev.mvc.survey_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Survey_itemCont{
  
  @Autowired
  @Qualifier("dev.mvc.survey_item.Survey_itemProc")
  private Survey_itemProcInter survey_itemProc;
  
  public Survey_itemCont() {
    System.out.println("--> SurveyCont created.");
  }
  
  /**
   * 등록폼 http://localhost:9090/team2/survey_item/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey_item/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey_item/create"); 

    return mav;
  }
  
  /**
   * 등록처리 http://localhost:9090/team2/survey_item/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/survey_item/create.do", method = RequestMethod.POST)
  public ModelAndView create(Survey_itemVO survey_itemVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey_item/create_msg");

    int cnt = this.survey_itemProc.create(survey_itemVO);
    mav.addObject("cnt", cnt);

    return mav;
  }
  
}