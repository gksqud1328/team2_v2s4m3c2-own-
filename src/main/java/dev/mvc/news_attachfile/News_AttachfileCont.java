package dev.mvc.news_attachfile;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.news.NewsProcInter;
import dev.mvc.news.NewsVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class News_AttachfileCont {
  @Autowired
  @Qualifier("dev.mvc.news.NewsProc")
  private NewsProcInter newsProc; 
  
  @Autowired
  @Qualifier("dev.mvc.news_attachfile.News_AttachfileProc")
  private News_AttachfileProcInter news_attachfileProc;
  
  public News_AttachfileCont() {
    System.out.println("--> News_AttachfileCont created.");
  } 
  
  ////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * ��� �� http://localhost:9090/news/attachfile/create.do X
   * http://localhost:9090/team2/news_attachfile/create.do?newsgrp_no=1&news_no=22 O
   * 
   * @return
   */
  @RequestMapping(value = "/news_attachfile/create.do", method = RequestMethod.GET)
  public ModelAndView create(int news_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/news_attachfile/create"); // webapp/news_attachfile/create.jsp

    return mav;
  }
  
   /**
   * ��� ó��
   * @param ra
   * @param request
   * @param attachfileVO
   * @param newsgrp_no
   * @return
   */
  @RequestMapping(value = "/news_attachfile/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, News_AttachfileVO news_AttachfileVO, int newsgrp_no) {

    ModelAndView mav = new ModelAndView();
    // ---------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // ---------------------------------------------------------------
    int news_no = news_AttachfileVO.getNews_no(); // �θ�� ��ȣ
    String news_attachfile_rname = ""; // ���� ���ϸ�
    String news_attachfile_upname = ""; // ���ε�� ���ϸ�
    long news_attachfile_size = 0; // ���� ������
    String news_attachfile_thumb = ""; // Preview �̹���
    int upload_count = 0; // ����ó���� ���ڵ� ����

    String upDir = Tool.getRealPath(request, "/news_attachfile/storage");

    // ���� ������ ����� fnamesMF ��ü�� ������.
    List<MultipartFile> news_attachfile_rnamesMF = news_AttachfileVO.getNews_attachfile_rnamesMF();

    int count = news_attachfile_rnamesMF.size(); // ���� ���� ����
    if (count > 0) {
      for (MultipartFile multipartFile : news_attachfile_rnamesMF) { // ���� ����, 1���̻� ���� ó��
        news_attachfile_size = multipartFile.getSize(); // ���� ũ��
        if (news_attachfile_size > 0) { // ���� ũ�� üũ
          news_attachfile_rname = multipartFile.getOriginalFilename(); // ���� ���ϸ�
          news_attachfile_upname = Upload.saveFileSpring(multipartFile, upDir); // ���� ����, ���ε�� ���ϸ�

          if (Tool.isImage(news_attachfile_rname)) { // �̹������� �˻�
            news_attachfile_thumb = Tool.preview(upDir, news_attachfile_upname, 200, 150); // thumb �̹��� ����
          }
        }
        News_AttachfileVO vo = new News_AttachfileVO();
        vo.setNews_no(news_no);
        vo.setNews_attachfile_rname(news_attachfile_rname);
        vo.setNews_attachfile_upname(news_attachfile_upname);
        vo.setNews_attachfile_thumb(news_attachfile_thumb);
        vo.setNews_attachfile_size(news_attachfile_size);

        // ���� 1�� ��� ���� dbms ����, ������ 20���̸� 20���� record insert.
        upload_count = upload_count + news_attachfileProc.create(vo);
      }
    }
    // -----------------------------------------------------
    // ���� ���� �ڵ� ����
    // -----------------------------------------------------

    mav.addObject("news_no", news_no); // redirect parameter ����
    mav.addObject("newsgrp_no", newsgrp_no); // redirect parameter ����
    mav.addObject("upload_count", upload_count); // redirect parameter ����
    mav.addObject("url", "create_msg"); // create_msg.jsp, redirect parameter ����
    mav.setViewName("redirect:/news_attachfile/msg.do"); // ���ΰ�ħ ����
    return mav;
  }

   /**
   * ���ΰ�ħ�� �����ϴ� �޽��� ���
   * 
   * @param memberno
   * @return
   */
  @RequestMapping(value = "/news_attachfile/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    // ��� ó�� �޽���: create_msg --> /attachfile/create_msg.jsp
    // ���� ó�� �޽���: update_msg --> /attachfile/update_msg.jsp
    // ���� ó�� �޽���: delete_msg --> /attachfile/delete_msg.jsp
    mav.setViewName("/news_attachfile/" + url); // forward
    //mav.setViewName("/news_attachfile/create_msg"); // forward

    return mav; // forward
  }
  
   /**
   * ��� http://localhost:9090/team2/news_attachfile/list.do
   * @return
   */
  @RequestMapping(value = "/news_attachfile/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    List<News_AttachfileVO> list = this.news_attachfileProc.list();
    
    mav.addObject("list", list);
    mav.setViewName("/news_attachfile/list");

    return mav;
  }
  
   /**
   * �ϳ��� news_no�� ��� 
   * http://localhost:9090/team2/news_attachfile/list_by_news_no.do
   * @return
   */
  @RequestMapping(value = "/news_attachfile/list_by_news_no.do", method = RequestMethod.GET)
  public ModelAndView list_by_news_no(int news_no) {
    ModelAndView mav = new ModelAndView();

    List<News_AttachfileVO> list = news_attachfileProc.list_by_news_no(news_no);
    mav.addObject("list", list);

    NewsVO newsVO = this.newsProc.read(news_no);
    mav.addObject("newsVO", newsVO);
    mav.setViewName("/news_attachfile/list_by_news_no"); // list_by_news_no.jsp

    return mav; 
  }
  
  
   /** 
   * ÷�� ���� 1�� ���� ó��
   * 
   * @return
   */
  @RequestMapping(value = "/news_attachfile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(HttpServletRequest request, int news_attachfile_no,
    @RequestParam(value = "news_no", defaultValue = "0") int news_no, String rurl) {
    ModelAndView mav = new ModelAndView();

    // ������ ���� ������ �о��.
    News_AttachfileVO news_AttachfileVO = news_attachfileProc.read(news_attachfile_no);

    String upDir = Tool.getRealPath(request, "/news_attachfile/storage"); // ���� ���
    Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_rname()); // Folder���� 1���� ���� ����
    Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_thumb()); // 1���� Thumb ���� ����

    // DBMS���� 1���� ���� ����
    news_attachfileProc.delete(news_attachfile_no);

    List<News_AttachfileVO> list = news_attachfileProc.list(); // ��� ���� ��ħ
    mav.addObject("list", list);

    mav.addObject("news_no", news_no);

    mav.setViewName("redirect:/news_attachfile/" + rurl);

    return mav;
  }
  

 /**
  * FK�� ����� ���ڵ� ����
  * http://localhost:9090/team2/news_attachfile/list_by_news_no.do?
  * @param request
  * @param news_no
  * @return
  */
 @ResponseBody
 @RequestMapping(value = "/news_attachfile/delete_by_news_no.do", method = RequestMethod.POST,
                         produces = "text/plain;charset=UTF-8")
 public String delete_by_news_no(HttpServletRequest request,
                                             @RequestParam(value = "news_no", defaultValue = "0") int news_no) {
   try {
     Thread.sleep(3000);
   } catch (InterruptedException e) {
     e.printStackTrace();
   }
   
   List<News_AttachfileVO> list = this.news_attachfileProc.list_by_news_no(news_no);
   int cnt = 0; // ������ ���ڵ� ����

   String upDir = Tool.getRealPath(request, "/news_attachfile/storage"); // ���� ���
   
   for (News_AttachfileVO news_AttachfileVO: list) { // ���� ������ŭ ��ȯ
     Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_rname()); // Folder���� 1���� ���� ����
     Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_thumb()); // 1���� Thumb ���� ����
   
     news_attachfileProc.delete(news_AttachfileVO.getNews_attachfile_no());  // DBMS���� 1���� ���� ����
     cnt = cnt + 1;

   }
   
   JSONObject json = new JSONObject();
   json.put("cnt", cnt);

   return json.toString();
 }

  
  /**
  * �θ�Ű�� ���� ����
  * //http://localhost:9090/team2/news_attachfile/count_by_news_no.do?
  * @return
  */
 @ResponseBody
 @RequestMapping(value = "/news_attachfile/count_by_news_no.do", method = RequestMethod.GET, 
                         produces = "text/plain;charset=UTF-8")
 public String count_by_news_no(int news_no) {
   try {
     Thread.sleep(3000);
   } catch (InterruptedException e) {
     e.printStackTrace();
   }
   
   int cnt = this.news_attachfileProc.count_by_news_no(news_no);

   JSONObject json = new JSONObject();
   json.put("cnt", cnt);

   return json.toString();
 }

}
