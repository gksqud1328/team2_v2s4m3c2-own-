package dev.mvc.notice;

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
public class NoticeCont {

  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;

  public NoticeCont() {
    System.out.println("--> NoticeCont created.");
  }

  /**
   * ����� http://localhost:9090/team2/notice/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/create"); // /notice/create.jsp

    return mav;
  }

  /**
   * ���ó�� http://localhost:9090/team2/notice/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/create.do", method = RequestMethod.POST)
  public ModelAndView create(NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/create_msg");

    int cnt = this.noticeProc.create(noticeVO);
    mav.addObject("cnt", cnt);

    return mav;
  }

  /**
   * ��� http://localhost:9090/team2/notice/list.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/list"); // /webapp/notice/list.jsp

    // ��� ��ȣ �� ���� //List<NoticeVO> list = this.noiceProc.list();
    /* ������ ��ȣ �� ���� */ List<NoticeVO> list = this.noticeProc.list();
    mav.addObject("list", list);

    return mav; // forward
  }

  /**
   * ��ȸhttp://localhost:9090/team2/notice/read.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/read.do", method = RequestMethod.GET)
  public ModelAndView read(int noticeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/read"); // /webapp/notice/read.jsp
    
    int cnt = this.noticeProc.update_count(noticeno);
    mav.addObject("cnt", cnt);
    
    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    List<NoticeVO> list = this.noticeProc.list();
    mav.addObject("list", list);

    return mav; // forward
  }

  /**
   * ���� �� http://localhost:9090/resort/notice/update.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.GET)
  public ModelAndView update(int noticeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/update"); // /webapp/notice/ update.jsp

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    List<NoticeVO> list = this.noticeProc.list();
    mav.addObject("list", list);
 
    return mav; // forward
  } 
  
  /**
   * ���� ó��
   * 
   * @param noticeVO
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.POST)
  public ModelAndView update(NoticeVO noticeVO) {

    ModelAndView mav = new ModelAndView();

    int cnt = this.noticeProc.update(noticeVO);
    mav.addObject("cnt", cnt); // request�� ����
    
    mav.setViewName("/notice/update_msg");

    return mav;
  }

  /**
   * ������ http://localhost:9090/team2/notice/delete.do
   * @return
   */
  @RequestMapping(value = "/notice/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int noticeno) {
    ModelAndView mav = new ModelAndView(); 

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);
    
    mav.setViewName("/notice/read_delete"); // /webapp/notice/delete.jsp

    return mav; // forward
  }

  /**
   * ���� ó��
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/notice/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, 
                            int noticeno, String passwd) {
    ModelAndView mav = new ModelAndView();

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    String head = noticeVO.getHead();
    mav.addObject("head", head);
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("noticeno", noticeno);
    hashMap.put("passwd", passwd);
    
    
    int passwd_cnt=0;
    int cnt = 0; 
    
    passwd_cnt = this.noticeProc.passwd_check(hashMap);
    boolean sw= false;
     
    if(passwd_cnt == 1) {
      cnt = this.noticeProc.delete(noticeno);
    } 
    
    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����

    mav.setViewName("/notice/delete_msg"); // /webapp/notice/delete_msg.jsp

    return mav;
  }

  /**
   * �켱���� ���� up 1 �� 10
   * 
   * @param noticeno �������� �� ��ȣ
   * @return
   */
  @RequestMapping(value = "/notice/update_noticeno_up.do", method = RequestMethod.GET)
  public ModelAndView update_noticeno_up(int noticeno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.noticeProc.update_noticeno_up(noticeno);
    mav.addObject("cnt", cnt); // request�� ����

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    mav.setViewName("/notice/update_noticeno_up"); // /notice/update_noticeno_up.jsp

    return mav;
  }

  /**
   * �켱���� ���� down 1 �� 10
   * 
   * @param noticeno �������� �� ��ȣ
   * @return
   */
  @RequestMapping(value = "/notice/update_noticeno_down.do", method = RequestMethod.GET)
  public ModelAndView update_noticeno_down(int noticeno) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.noticeProc.update_noticeno_down(noticeno);
    mav.addObject("cnt", cnt); // request�� ����

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    mav.setViewName("/notice/update_noticeno_down"); // /notice/update_noticeno_down.jsp

    return mav;
  }

  /**
   * ��¸�� ����
   * 
   * @param noticeVO
   * @return
   */
  @RequestMapping(value = "/notice/update_visible.do", method = RequestMethod.GET)
  public ModelAndView update_visible(NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.noticeProc.update_visible(noticeVO);
    mav.addObject("cnt", cnt); // request�� ����

    if (cnt == 1) { 
      mav.setViewName("redirect:/notice/list.do?noticeno=" + noticeVO.getNoticeno()); // request ��ü�� ������ �ȵ�. 
    } else {
      NoticeVO vo = this.noticeProc.read(noticeVO.getNoticeno());
      String head = vo.getHead();
      mav.addObject("head", head);
      mav.setViewName("/notice/update_visible_msg"); // 
    }
    return mav;
  }

}
