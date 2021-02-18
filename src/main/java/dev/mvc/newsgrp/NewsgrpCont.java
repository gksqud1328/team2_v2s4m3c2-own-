package dev.mvc.newsgrp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsgrpCont {
  
  @Autowired
  @Qualifier("dev.mvc.newsgrp.NewsgrpProc")
  private NewsgrpProcInter newsgrpProc;
  
  public NewsgrpCont() {
    System.out.println("--> NewsgrpCont created.");
  }

  
  /** 
   * http://localhost:9090/team2/newsgrp/create.do
   * �����
   * @return
   */
  @RequestMapping(value = "/newsgrp/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/newsgrp/create");
    
    return mav;
  }
  
   /**
   * ��� ó��
   * @param newsgrpVO
   * @return
   */
  @RequestMapping(value = "/newsgrp/create.do", method = RequestMethod.POST)
  public ModelAndView create(NewsgrpVO newsgrpVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.newsgrpProc.create(newsgrpVO);
    
    if (cnt == 0) {
      mav.addObject("cnt", cnt);
      mav.addObject("url", "create_msg");
      mav.setViewName("redirect:/newsgrp/msg.do");
    } else {
      mav.setViewName("redirect:/newsgrp/list.do");
    }
    
    return mav;
  }
  
   /**
   * Ajax + create
   * @param newsgrpVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/newsgrp/create_ajax.do", method = RequestMethod.POST, 
                          produces = "text/plain;charset=UTF-8")
  public String create_ajax(NewsgrpVO newsgrpVO) {

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    int cnt = this.newsgrpProc.create(newsgrpVO);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    
    return json.toString();
  }
  
   /**
   * ��� http://localhost:9090/team2/newsgrp/list_ajax.do
   *
   * @return
   */
  @RequestMapping(value = "/newsgrp/list_ajax.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    //List<NewsgrpVO> list = this.newsgrpProc.list_newsgrp_no_asc();
    List<NewsgrpVO> list = this.newsgrpProc.list_newsgrp_seqno_asc();          
    mav.addObject("list", list);
    
    //mav.setViewName("/newsgrp/list");
    mav.setViewName("/newsgrp/list_ajax");                                                              
    return mav;
  }
  
  
  
   /**
   * ��ȸ + ������ http://localhost:9090/team2/newsgrp/read_update.do
   * @param newsgrp_no
   * @return
   */
  @RequestMapping(value = "/newsgrp/read_update.do", method = RequestMethod.GET)
  public ModelAndView read(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/newsgrp/read");
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
   
    List<NewsgrpVO> list = this.newsgrpProc.list_newsgrp_seqno_asc();
    mav.addObject("list", list);
    
    return mav;
  }
  
   /**
   * Ajax + read  http://localhost:9090/team2/newsgrp/read_ajax.do
   * @param newsgrp_no
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/newsgrp/read_ajax.do", method = RequestMethod.GET,
                          produces = "text/plain;charset=UTF-8")
  public String read_ajax(int newsgrp_no) {
    
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    
    JSONObject json = new JSONObject();
    json.put("newsgrp_no", newsgrp_no);
    json.put("newsgrp_head", newsgrpVO.getNewsgrp_head());
    json.put("newsgrp_seqno", newsgrpVO.getNewsgrp_seqno());
    
    return json.toString();
  }
  
   /**
   * ���� ó��
   * @param newsgrpVO
   * @return
   */
  @RequestMapping(value = "/newsgrp/update.do", method = RequestMethod.POST)
  public ModelAndView update(NewsgrpVO newsgrpVO) {
 // CategrpVO categrpVO <FORM> �±��� ������ �ڵ� ������.
    // request.setAttribute("categrpVO", categrpVO); �ڵ� ����
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.newsgrpProc.update(newsgrpVO);
    mav.addObject("cnt", cnt);// request�� ����
    
    
    mav.setViewName("/newsgrp/update_msg");
    
    return mav;
  }
  
   /**
   * Ajax + update_proc  http://localhost:9090/team2/newsgrp/update_ajax.do
   * @param newsgrpVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/newsgrp/update_ajax.do", method = RequestMethod.POST, 
                          produces = "text/plain;charset=UTF-8") 
  public String update_ajax(NewsgrpVO newsgrpVO) {
    
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    int cnt = this.newsgrpProc.update(newsgrpVO);
    
    JSONObject json = new JSONObject(); 
    json.put("cnt", cnt);

    return json.toString();
  }
   
   /**
   * ������ http://localhost:9090/team2/newsgrp/read_delete.do
   * @param newsgrp_no
   * @return
   */
  @RequestMapping(value = "/newsgrp/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/newsgrp/read_delete");    
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
    
    List<NewsgrpVO> list = this.newsgrpProc.list_newsgrp_seqno_asc();
    mav.addObject("list", list);
    
    return mav;
  }
   
  /**
   * ���� ó��
   * @param newsgrp_no
   * @return
   */
  @RequestMapping(value = "/newsgrp/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.newsgrpProc.delete(newsgrp_no);

    //  mav.addObject("cnt", cnt);
    
    //  mav.setViewName("/newsgrp/delete_msg");
    if (cnt == 0) {
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

      mav.addObject("url", "delete_msg"); // create_msg.jsp, redirect parameter ����
      mav.setViewName("redirect:/newsgrp/msg.do"); 
      
    } else {
      mav.setViewName("redirect:/newsgrp/list.do");
    }
    
    return mav;
  }
  
   /**
   * delete + ajax
   * @param newsgrp_no
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/newsgrp/delete_ajax.do", method = RequestMethod.POST,
                          produces = "text/plain;charset=UTF-8") 
  public String delete_ajax(int newsgrp_no) {   
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    int cnt = this.newsgrpProc.delete(newsgrp_no);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);

    return json.toString();
  }
  
   /**
   * �켱���� ����
   * @param newsgrp_no
   * @return
   */
  @RequestMapping(value = "/newsgrp/update_newsgrp_seqno_up_msg.do", method = RequestMethod.GET)
  public ModelAndView update_newsgrp_seqno_up(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.newsgrpProc.update_newsgrp_seqno_up(newsgrp_no);
    mav.addObject("cnt", cnt);
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
    
    mav.setViewName("redirect:/newsgrp/list_ajax.do");
    
    return mav;
  }
  
   /**
   * �켱���� ����
   * @param newsgrp_no
   * @return
   */
  @RequestMapping(value = "/newsgrp/update_newsgrp_seqno_down_msg.do", method = RequestMethod.GET)
  public ModelAndView update_newsgrp_seqno_down(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.newsgrpProc.update_newsgrp_seqno_down(newsgrp_no);
    mav.addObject("cnt", cnt);
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
    
    mav.setViewName("redirect:/newsgrp/list_ajax.do");
    
    return mav;
  }
  
   /**
   * ���ΰ�ħ�� �����ϴ� �޽��� ���
   * @param url
   * @return
   */
  @RequestMapping(value = "/newsgrp/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/newsgrp/" + url);
    
    return mav;
  }

}
