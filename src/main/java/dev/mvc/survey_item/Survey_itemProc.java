package dev.mvc.survey_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.survey_item.Survey_itemProc")
public class Survey_itemProc implements Survey_itemProcInter {
  @Autowired
  private Survey_itemDAOInter survey_itemDAO;
  
  public Survey_itemProc() {
    System.out.println("--> Survey_itemProc created.");
  }
  @Override
  public int create(Survey_itemVO survey_itemVO) {
    int cnt = this.survey_itemDAO.create(survey_itemVO);
    return cnt;
  }
}
