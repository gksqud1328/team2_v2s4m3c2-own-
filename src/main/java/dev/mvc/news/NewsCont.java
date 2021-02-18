package dev.mvc.news;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.newsgrp.NewsgrpProcInter;
import dev.mvc.newsgrp.NewsgrpVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class NewsCont {
 
  @Autowired
  @Qualifier("dev.mvc.newsgrp.NewsgrpProc")
  private NewsgrpProcInter newsgrpProc;
  
  @Autowired
  @Qualifier("dev.mvc.news.NewsProc")
  private NewsProcInter newsProc;
  
  public NewsCont() {
    System.out.println("--> NewsCont created."); 
  } 

  /**
   * ����� http://localhost:9090/team2/news/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/create.do", method = RequestMethod.GET)
  public ModelAndView create(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    
    mav.addObject("newsgrpVO", newsgrpVO);
    
    mav.setViewName("/news/create"); // /webapp/categrp/create.jsp
    // String content = "���:\n�ο�:\n�غ�:\n���:\n��Ÿ:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }

  /**
   * ��� ó�� http://localhost:9090/team2/news/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/create.do", method = RequestMethod.POST)
  public @ResponseBody ModelAndView create(HttpServletRequest request, NewsVO newsVO) {
    // System.out.println("IP: " + contentsVO.getIp());  // Oracle�� "" -> null�� �ν�
    // System.out.println("IP: " + request.getRemoteAddr());     
    
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // ���� ���
    
    // ���� ������ ����� fnamesMF ��ü�� ������.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="���� ����" multiple="multiple">
    MultipartFile mf = newsVO.getFile1MF();
    
    long size1 = mf.getSize();  // ���� ũ��
    if (size1 > 0) { // ���� ũ�� üũ
      // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
      // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // �̹������� �˻�
        // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 200, height: 150
        thumb1 = Tool.preview(upDir, file1, 200, 150); 
      }
      
    }    
    
    newsVO.setFile1(file1);
    newsVO.setThumb1(thumb1);
    newsVO.setSize1(size1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    // Call By Reference: �޸� ����, Hashcode ����
    int cnt = this.newsProc.create(newsVO); 
    
    // -------------------------------------------------------------------
    // PK�� return
    // -------------------------------------------------------------------
    System.out.println("--> news_no: " + newsVO.getNews_no());
    mav.addObject("news_no", newsVO.getNews_no()); // redirect parameter ����
    // -------------------------------------------------------------------
    
//    if (cnt == 1) {
//      newsgrpProc.increaseCnt(newsVO.getNewsgrp_no());
//    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    // <c:import> ���� �۵��ȵ�.
    // mav.setViewName("/contents/create_msg"); // /webapp/news/create_msg.jsp
    
    // System.out.println("--> cateno: " + contentsVO.getCateno());
    // redirect�ÿ� hidden tag�� �����͵��� ������ �ȵ����� request�� �ٽ� ����
    mav.addObject("newsgrp_no", newsVO.getNewsgrp_no()); // redirect parameter ����
    mav.addObject("url", "create_continue"); // create_continue.jsp, redirect parameter ����
    //mav.setViewName("redirect:/news/msg.do"); 
    mav.setViewName("/news/create_msg"); // /webapp/news/create_msg.jsp
    return mav; // forward
  }

  /**
   * ��� �׷��� ��ü��� 
   * http://localhost:9090/team2/news/list_all.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/list_all.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/news/list_all");

    // /webapp/news/list.jsp
    List<NewsVO> list = this.newsProc.list_all();
    mav.addObject("list", list);

    return mav; // forward
  }

  /**
   * ���� ī�װ��� ���(�����)
   * http://localhost:9090/team2/news/list.do
   * @return
   */
  @RequestMapping(value = "/news/list.do", method = RequestMethod.GET)
  public ModelAndView list(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    // ���̺� �̹��� ���, /webapp/news/list_by_newsgrp_no_grid1.jsp
    mav.setViewName("/news/list");
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
    
    List<NewsVO> list = this.newsProc.list(newsgrp_no);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * ���� ī�װ��� ���(�׸�����)
   * http://localhost:9090/team2/news/list_by_newsgrp_no_grid1.do
   * @return
   */
  @RequestMapping(value = "/news/list_by_newsgrp_no_grid1.do", method = RequestMethod.GET)
  public ModelAndView list_by_newsgrp_no_grid1(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    // ���̺� �̹��� ���, /webapp/news/list_by_newsgrp_no_grid1.jsp
    mav.setViewName("/news/list_by_newsgrp_no_grid1");

    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
    
    List<NewsVO> list = this.newsProc.list_by_newsgrp_no(newsgrp_no);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  // http://localhost:9090/team2/news/read.do
  /**
   * ��ȸ
   * @return
   */
  @RequestMapping(value="/news/read.do", method=RequestMethod.GET )
  public ModelAndView read(int news_no) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.newsProc.update_count(news_no);
    mav.addObject("cnt", cnt);
    
    NewsVO newsVO = this.newsProc.read(news_no);
    mav.addObject("newsVO", newsVO); // request.setAttribute("newsVO", newsVO);

    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsVO.getNewsgrp_no());
    mav.addObject("newsgrpVO", newsgrpVO); 
    
    // ÷�� ���� ���
    // List<AttachfileVO> attachfile_list = this.attachfileProc.list_by_news_no(news_no);
    //mav.addObject("attachfile_list", attachfile_list);
    //System.out.println("--> ÷�� ���� ����: " + attachfile_list.size());
    
    // mav.setViewName("/news/read"); // /webapp/news/read.jsp
    // mav.setViewName("/news/read_img"); // /webapp/news/read_img.jsp
    // mav.setViewName("/news/read_img_attachfile"); // /webapp/news/read_img_attachfile.jsp    
    // mav.setViewName("/news/read_img_attachfile_reply"); // /webapp/news/read_img_attachfile_reply.jsp
    // mav.setViewName("/news/read_img_attachfile_reply_add"); // /webapp/news/read_img_attachfile_reply_add.jsp
    //mav.setViewName("/news/read_img_attachfile_reply_add_pg"); // /webapp/news/read_img_attachfile_reply_add_pg.jsp
    
    return mav;
  }
  
//  /**
//   * ��ȸhttp://localhost:9090/team2/news/read.do
//   * 
//   * @return
//   */
//  @RequestMapping(value = "/news/read.do", method = RequestMethod.GET)
//  public ModelAndView read(int news_no) {
//    ModelAndView mav = new ModelAndView();
//    mav.setViewName("/news/read"); // /webapp/team2/read.jsp
//
//    NewsVO newsVO = this.newsProc.read(news_no);
//    mav.addObject("newsVO", newsVO);
//
//    List<NewsVO> list = this.newsProc.list();
//    mav.addObject("list", list);
//
//    return mav; // forward
//  }

//http://localhost:9090/team2/news/update.do
 /**
  * ���� ��
  * @return
  */
 @RequestMapping(value="/news/update.do", method=RequestMethod.GET )
 public ModelAndView update(int news_no) {
   ModelAndView mav = new ModelAndView();
   
   NewsVO newsVO = this.newsProc.read_update(news_no); // ������ �б�
   mav.addObject("newsVO", newsVO); // request.setAttribute("newsVO", newsVO);
   
   mav.setViewName("/news/update"); // webapp/news/update.jsp
   
   return mav;
 }
 
  /**
   * ���� ó�� http://localhost:9090/team2/news/update.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/update.do", method = RequestMethod.POST)
  public ModelAndView update(NewsVO newsVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/news/update");

    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsVO.getNewsgrp_no());
    
    mav.addObject("newsgrp_head", newsgrpVO.getNewsgrp_head());
    mav.addObject("newsgrp_no", newsgrpVO.getNewsgrp_no());
    
    mav.addObject("news_no", newsVO.getNews_no());
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("news_no", newsVO.getNews_no());
    hashMap.put("news_passwd", newsVO.getNews_passwd());

    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0; // ������ ���ڵ� ����

    passwd_cnt = this.newsProc.passwd_check(hashMap);

    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� �� ����
      cnt = this.newsProc.update(newsVO);
    }

    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����

    mav.setViewName("/news/update_msg");

    return mav; // forward
  }

//http://localhost:9090/team2/news/delete.do
  /**
   * ���� ��, attachfile Ajax ��� ���� ����
   * @return
   */
  @RequestMapping(value="/news/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int news_no) {
    ModelAndView mav = new ModelAndView();
    
    NewsVO newsVO = this.newsProc.read_update(news_no); // ������ �б�
    mav.addObject("newsVO", newsVO); // request.setAttribute("newsVO", newsVO);
    
    mav.setViewName("/news/delete"); // webapp/news/delete.jsp
    
    return mav;
  }

  //http://localhost:9090/team2/news/delete.do
  /**
  * ���� ó�� +  ���� ����
  * @param newsVO
  * @return
  */
  @RequestMapping(value="/news/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(HttpServletRequest request,
                                          int newsgrp_no, 
                                          int news_no, 
                                          String news_passwd) {
   ModelAndView mav = new ModelAndView();
  
   NewsVO newsVO = this.newsProc.read(news_no);
   String news_head = newsVO.getNews_head();
   mav.addObject("news_head", news_head);
   
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("news_no", news_no);
   hashMap.put("news_passwd", news_passwd);
   
   int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
   int cnt = 0;             // ������ ���ڵ� ���� 
   
   passwd_cnt = this.newsProc.passwd_check(hashMap);
   boolean sw = false;
   
   if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� �� ����
     cnt = this.newsProc.delete(news_no);
     if (cnt == 1) {
       //newsgrpProc.decreaseCnt(newsgrp_no);
       // -------------------------------------------------------------------------------------
       // ������ �������� ���ڵ� �������� ������ ��ȣ -1 ó��
       // HashMap<String, Object> map = new HashMap<String, Object>();
       // map.put("newsgrp_no", newsgrp_no);
       // map.put("word", word);
       // �ϳ��� �������� 3���� ���ڵ�� �����Ǵ� ��� ���� 9���� ���ڵ尡 ���� ������
       // if (newsProc.search_count(map) % Contents.RECORD_PER_PAGE == 0) {
       //  nowPage = nowPage - 1;
       //  if (nowPage < 1) {
       //   nowPage = 1; // ���� ������
       //  }
       //}
       // -------------------------------------------------------------------------------------
     }
     
  //   String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // ���� ���
  //   sw = Tool.deleteFile(upDir, newsVO.getFile1());  // Folder���� 1���� ���� ����
  //   sw = Tool.deleteFile(upDir, newsVO.getThumb1());  // Folder���� 1���� ���� ����
  
   }
  
   mav.addObject("cnt", cnt); // request�� ����
   mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
   // mav.addObject("nowPage", nowPage); // request�� ����
   // System.out.println("--> NewsCont.java nowPage: " + nowPage);
   
   mav.addObject("newsgrp_no", newsVO.getNewsgrp_no()); // redirect parameter ����
   mav.addObject("url", "delete_msg"); // delete_msg.jsp, redirect parameter ����
   
   // mav.setViewName("/news/delete_msg"); // webapp/news/delete_msg.jsp
   mav.setViewName("redirect:/news/msg.do"); 
   
   return mav;
  }
  /**
   * ���� �̹��� ��� �� http://localhost:9090/team2/news/img_create.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/img_create.do", method = RequestMethod.GET)
  public ModelAndView img_create(int news_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/news/img_create"); // /webapp/news/img_create.jsp
    
    NewsVO newsVO = this.newsProc.read(news_no);
    mav.addObject("newsVO", newsVO);
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsVO.getNewsgrp_no());
    mav.addObject("newsgrpVO", newsgrpVO);
    
    return mav; // forward
  }
  
  /**
   * ���� �̹��� ��� ó�� http://localhost:9090/team2/news/img_create.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/img_create.do", method = RequestMethod.POST)
  public ModelAndView img_create(HttpServletRequest request, 
                                    NewsVO newsVO) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("news_no", newsVO.getNews_no());
    hashMap.put("news_passwd", newsVO.getNews_passwd());
    
    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0;             // ������ ���ڵ� ���� 
    
    passwd_cnt = this.newsProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� ���� ���ε�
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      String file1 = "";     // main image
      String thumb1 = ""; // preview image
          
      String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // ���� ���
      // ���� ������ ����� fnamesMF ��ü�� ������.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="���� ����" multiple="multiple">
      MultipartFile mf = newsVO.getFile1MF();
      long size1 = mf.getSize();  // ���� ũ��
      if (size1 > 0) { // ���� ũ�� üũ
        // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
        // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
        file1 = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1)) { // �̹������� �˻�
          // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1, 200, 150); 
        }
      }    
      
      newsVO.setFile1(file1);
      newsVO.setThumb1(thumb1);
      newsVO.setSize1(size1);
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      
      // mav.addObject("nowPage", nowPage);
      mav.addObject("news_no", newsVO.getNews_no());
      
      mav.setViewName("redirect:/news/read.do");
      
      cnt = this.newsProc.img_create(newsVO);
      // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.setViewName("/news/update_msg"); // webapp/news/update_msg.jsp
      
    }

    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
            
    return mav;    
  }  

  /**
   * ���� �̹��� ����/���� �� http://localhost:9090/team2/news/img_update.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/img_update.do", method = RequestMethod.GET)
  public ModelAndView img_update(int news_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/news/img_update"); // /webapp/news/img_update.jsp
  
    NewsVO newsVO = this.newsProc.read(news_no);
    mav.addObject("newsVO", newsVO);
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsVO.getNewsgrp_no());
    mav.addObject("newgrpsVO", newsgrpVO);  
    
    return mav; // forward
  }

  /**
   * ���� �̹��� ���� ó�� http://localhost:9090/team2/news/img_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/img_delete.do", method = RequestMethod.POST)
  public ModelAndView img_delete(HttpServletRequest request,
                                       int news_no, 
                                       int newsgrp_no, 
                                       String news_passwd){
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("news_no", news_no);
    hashMap.put("news_passwd", news_passwd);
    
    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0;             // ������ ���ڵ� ���� 
    
    passwd_cnt = this.newsProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� ���� ���ε�
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      // ������ ���� ������ �о��.
      NewsVO newsVO = newsProc.read(news_no);
      // System.out.println("file1: " + contentsVO.getFile1());
      
      String file1 = newsVO.getFile1().trim();
      String thumb1 = newsVO.getThumb1().trim();
      long size1 = newsVO.getSize1();
      boolean sw = false;
      
      String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // ���� ���
      sw = Tool.deleteFile(upDir, newsVO.getFile1());  // Folder���� 1���� ���� ����
      sw = Tool.deleteFile(upDir, newsVO.getThumb1());  // Folder���� 1���� ���� ����
      // System.out.println("sw: " + sw);
      
      file1 = "";
      thumb1 = "";
      size1 = 0;
      
      newsVO.setFile1(file1);
      newsVO.setThumb1(thumb1);
      newsVO.setSize1(size1);
      // -------------------------------------------------------------------
      // ���� ���� ���� ����
      // -------------------------------------------------------------------
      
      // mav.addObject("nowPage", nowPage);  // redirect�ÿ� get parameter�� ���۵�
      mav.addObject("news_no", news_no);
      mav.setViewName("redirect:/news/read.do");
      
      cnt = this.newsProc.img_delete(newsVO);
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.setViewName("/news/update_msg"); // webapp/news/update_msg.jsp
      
    }
  
    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
            
    return mav;    
  }
  
  /**
   * ���� �̹��� ���� ó�� http://localhost:9090/team2/news/img_update.do
   * ���� �̹��� ������ ���ο� �̹��� ���(���� ó��)
   * @return
   */
  @RequestMapping(value = "/news/img_update.do", method = RequestMethod.POST)
  public ModelAndView img_update(HttpServletRequest request, 
                                     NewsVO newsVO) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("news_no", newsVO.getNews_no());
    hashMap.put("news_passwd", newsVO.getNews_passwd());
    
    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0;             // ������ ���ڵ� ���� 
    
    passwd_cnt = this.newsProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� ���� ���ε�
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      // ������ ���� ������ �о��.
      NewsVO vo = newsProc.read(newsVO.getNews_no());
      // System.out.println("file1: " + contentsVO.getFile1());
      
      String file1 = vo.getFile1().trim();
      String thumb1 = vo.getThumb1().trim();
      long size1 = 0;
      boolean sw = false;
      
      String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // ���� ���
      sw = Tool.deleteFile(upDir, newsVO.getFile1());  // Folder���� 1���� ���� ����
      sw = Tool.deleteFile(upDir, newsVO.getThumb1());  // Folder���� 1���� ���� ����
      // System.out.println("sw: " + sw);
      // -------------------------------------------------------------------
      // ���� ���� ���� ����
      // -------------------------------------------------------------------
      
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      // ���� ������ ����� fnamesMF ��ü�� ������.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="���� ����" multiple="multiple">
      MultipartFile mf = newsVO.getFile1MF();
      size1 = mf.getSize();  // ���� ũ��
      if (size1 > 0) { // ���� ũ�� üũ
        // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
        // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
        file1 = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1)) { // �̹������� �˻�
          // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1, 200, 150); 
        }
      }    
      
      newsVO.setFile1(file1);
      newsVO.setThumb1(thumb1);
      newsVO.setSize1(size1);
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
  
      // mav.addObject("nowPage", nowPage);
      mav.addObject("news_no", newsVO.getNews_no());
      mav.setViewName("redirect:/news/read.do");
      
      
      cnt = this.newsProc.img_create(newsVO);
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.setViewName("/news/update_msg"); // webapp/news/update_msg.jsp
      
    }
  
    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
            
    return mav;    
  }
  
  /**
   * ���ΰ�ħ�� �����ϴ� �޽��� ���
   * @return
   */
  @RequestMapping(value="/news/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    
    // ��� ó�� �޽���: create_msg --> /contents/create_msg.jsp
    // ���� ó�� �޽���: update_msg --> /contents/update_msg.jsp
    // ���� ó�� �޽���: delete_msg --> /contents/delete_msg.jsp
    mav.setViewName("/news/" + url); // forward
    
    return mav; // forward
  }
  
  
  
}