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
   * 등록폼 http://localhost:9090/team2/news/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/create.do", method = RequestMethod.GET)
  public ModelAndView create(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    
    mav.addObject("newsgrpVO", newsgrpVO);
    
    mav.setViewName("/news/create"); // /webapp/categrp/create.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }

  /**
   * 등록 처리 http://localhost:9090/team2/news/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/news/create.do", method = RequestMethod.POST)
  public @ResponseBody ModelAndView create(HttpServletRequest request, NewsVO newsVO) {
    // System.out.println("IP: " + contentsVO.getIp());  // Oracle은 "" -> null로 인식
    // System.out.println("IP: " + request.getRemoteAddr());     
    
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택" multiple="multiple">
    MultipartFile mf = newsVO.getFile1MF();
    
    long size1 = mf.getSize();  // 파일 크기
    if (size1 > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
        thumb1 = Tool.preview(upDir, file1, 200, 150); 
      }
      
    }    
    
    newsVO.setFile1(file1);
    newsVO.setThumb1(thumb1);
    newsVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.newsProc.create(newsVO); 
    
    // -------------------------------------------------------------------
    // PK의 return
    // -------------------------------------------------------------------
    System.out.println("--> news_no: " + newsVO.getNews_no());
    mav.addObject("news_no", newsVO.getNews_no()); // redirect parameter 적용
    // -------------------------------------------------------------------
    
//    if (cnt == 1) {
//      newsgrpProc.increaseCnt(newsVO.getNewsgrp_no());
//    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    // <c:import> 정상 작동안됨.
    // mav.setViewName("/contents/create_msg"); // /webapp/news/create_msg.jsp
    
    // System.out.println("--> cateno: " + contentsVO.getCateno());
    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    mav.addObject("newsgrp_no", newsVO.getNewsgrp_no()); // redirect parameter 적용
    mav.addObject("url", "create_continue"); // create_continue.jsp, redirect parameter 적용
    //mav.setViewName("redirect:/news/msg.do"); 
    mav.setViewName("/news/create_msg"); // /webapp/news/create_msg.jsp
    return mav; // forward
  }

  /**
   * 모든 그룹의 전체목록 
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
   * 뉴스 카테고리별 목록(목록형)
   * http://localhost:9090/team2/news/list.do
   * @return
   */
  @RequestMapping(value = "/news/list.do", method = RequestMethod.GET)
  public ModelAndView list(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    // 테이블 이미지 기반, /webapp/news/list_by_newsgrp_no_grid1.jsp
    mav.setViewName("/news/list");
    
    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
    
    List<NewsVO> list = this.newsProc.list(newsgrp_no);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * 뉴스 카테고리별 목록(그리드형)
   * http://localhost:9090/team2/news/list_by_newsgrp_no_grid1.do
   * @return
   */
  @RequestMapping(value = "/news/list_by_newsgrp_no_grid1.do", method = RequestMethod.GET)
  public ModelAndView list_by_newsgrp_no_grid1(int newsgrp_no) {
    ModelAndView mav = new ModelAndView();
    
    // 테이블 이미지 기반, /webapp/news/list_by_newsgrp_no_grid1.jsp
    mav.setViewName("/news/list_by_newsgrp_no_grid1");

    NewsgrpVO newsgrpVO = this.newsgrpProc.read(newsgrp_no);
    mav.addObject("newsgrpVO", newsgrpVO);
    
    List<NewsVO> list = this.newsProc.list_by_newsgrp_no(newsgrp_no);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  // http://localhost:9090/team2/news/read.do
  /**
   * 조회
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
    
    // 첨부 파일 목록
    // List<AttachfileVO> attachfile_list = this.attachfileProc.list_by_news_no(news_no);
    //mav.addObject("attachfile_list", attachfile_list);
    //System.out.println("--> 첨부 파일 갯수: " + attachfile_list.size());
    
    // mav.setViewName("/news/read"); // /webapp/news/read.jsp
    // mav.setViewName("/news/read_img"); // /webapp/news/read_img.jsp
    // mav.setViewName("/news/read_img_attachfile"); // /webapp/news/read_img_attachfile.jsp    
    // mav.setViewName("/news/read_img_attachfile_reply"); // /webapp/news/read_img_attachfile_reply.jsp
    // mav.setViewName("/news/read_img_attachfile_reply_add"); // /webapp/news/read_img_attachfile_reply_add.jsp
    //mav.setViewName("/news/read_img_attachfile_reply_add_pg"); // /webapp/news/read_img_attachfile_reply_add_pg.jsp
    
    return mav;
  }
  
//  /**
//   * 조회http://localhost:9090/team2/news/read.do
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
  * 수정 폼
  * @return
  */
 @RequestMapping(value="/news/update.do", method=RequestMethod.GET )
 public ModelAndView update(int news_no) {
   ModelAndView mav = new ModelAndView();
   
   NewsVO newsVO = this.newsProc.read_update(news_no); // 수정용 읽기
   mav.addObject("newsVO", newsVO); // request.setAttribute("newsVO", newsVO);
   
   mav.setViewName("/news/update"); // webapp/news/update.jsp
   
   return mav;
 }
 
  /**
   * 수정 처리 http://localhost:9090/team2/news/update.do
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

    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0; // 수정된 레코드 갯수

    passwd_cnt = this.newsProc.passwd_check(hashMap);

    if (passwd_cnt == 1) { // 패스워드가 일치할 경우 글 수정
      cnt = this.newsProc.update(newsVO);
    }

    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장

    mav.setViewName("/news/update_msg");

    return mav; // forward
  }

//http://localhost:9090/team2/news/delete.do
  /**
   * 삭제 폼, attachfile Ajax 기반 삭제 지원
   * @return
   */
  @RequestMapping(value="/news/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int news_no) {
    ModelAndView mav = new ModelAndView();
    
    NewsVO newsVO = this.newsProc.read_update(news_no); // 수정용 읽기
    mav.addObject("newsVO", newsVO); // request.setAttribute("newsVO", newsVO);
    
    mav.setViewName("/news/delete"); // webapp/news/delete.jsp
    
    return mav;
  }

  //http://localhost:9090/team2/news/delete.do
  /**
  * 삭제 처리 +  파일 삭제
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
   
   int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
   int cnt = 0;             // 수정된 레코드 갯수 
   
   passwd_cnt = this.newsProc.passwd_check(hashMap);
   boolean sw = false;
   
   if (passwd_cnt == 1) { // 패스워드가 일치할 경우 글 수정
     cnt = this.newsProc.delete(news_no);
     if (cnt == 1) {
       //newsgrpProc.decreaseCnt(newsgrp_no);
       // -------------------------------------------------------------------------------------
       // 마지막 페이지의 레코드 삭제시의 페이지 번호 -1 처리
       // HashMap<String, Object> map = new HashMap<String, Object>();
       // map.put("newsgrp_no", newsgrp_no);
       // map.put("word", word);
       // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
       // if (newsProc.search_count(map) % Contents.RECORD_PER_PAGE == 0) {
       //  nowPage = nowPage - 1;
       //  if (nowPage < 1) {
       //   nowPage = 1; // 시작 페이지
       //  }
       //}
       // -------------------------------------------------------------------------------------
     }
     
  //   String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // 절대 경로
  //   sw = Tool.deleteFile(upDir, newsVO.getFile1());  // Folder에서 1건의 파일 삭제
  //   sw = Tool.deleteFile(upDir, newsVO.getThumb1());  // Folder에서 1건의 파일 삭제
  
   }
  
   mav.addObject("cnt", cnt); // request에 저장
   mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
   // mav.addObject("nowPage", nowPage); // request에 저장
   // System.out.println("--> NewsCont.java nowPage: " + nowPage);
   
   mav.addObject("newsgrp_no", newsVO.getNewsgrp_no()); // redirect parameter 적용
   mav.addObject("url", "delete_msg"); // delete_msg.jsp, redirect parameter 적용
   
   // mav.setViewName("/news/delete_msg"); // webapp/news/delete_msg.jsp
   mav.setViewName("redirect:/news/msg.do"); 
   
   return mav;
  }
  /**
   * 메인 이미지 등록 폼 http://localhost:9090/team2/news/img_create.do
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
   * 메인 이미지 등록 처리 http://localhost:9090/team2/news/img_create.do
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
    
    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0;             // 수정된 레코드 갯수 
    
    passwd_cnt = this.newsProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // 패스워드가 일치할 경우 파일 업로드
      // -------------------------------------------------------------------
      // 파일 전송 코드 시작
      // -------------------------------------------------------------------
      String file1 = "";     // main image
      String thumb1 = ""; // preview image
          
      String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // 절대 경로
      // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택" multiple="multiple">
      MultipartFile mf = newsVO.getFile1MF();
      long size1 = mf.getSize();  // 파일 크기
      if (size1 > 0) { // 파일 크기 체크
        // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        file1 = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1, 200, 150); 
        }
      }    
      
      newsVO.setFile1(file1);
      newsVO.setThumb1(thumb1);
      newsVO.setSize1(size1);
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------
      
      // mav.addObject("nowPage", nowPage);
      mav.addObject("news_no", newsVO.getNews_no());
      
      mav.setViewName("redirect:/news/read.do");
      
      cnt = this.newsProc.img_create(newsVO);
      // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.setViewName("/news/update_msg"); // webapp/news/update_msg.jsp
      
    }

    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
            
    return mav;    
  }  

  /**
   * 메인 이미지 삭제/수정 폼 http://localhost:9090/team2/news/img_update.do
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
   * 메인 이미지 삭제 처리 http://localhost:9090/team2/news/img_delete.do
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
    
    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0;             // 수정된 레코드 갯수 
    
    passwd_cnt = this.newsProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // 패스워드가 일치할 경우 파일 업로드
      // -------------------------------------------------------------------
      // 파일 삭제 코드 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      NewsVO newsVO = newsProc.read(news_no);
      // System.out.println("file1: " + contentsVO.getFile1());
      
      String file1 = newsVO.getFile1().trim();
      String thumb1 = newsVO.getThumb1().trim();
      long size1 = newsVO.getSize1();
      boolean sw = false;
      
      String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // 절대 경로
      sw = Tool.deleteFile(upDir, newsVO.getFile1());  // Folder에서 1건의 파일 삭제
      sw = Tool.deleteFile(upDir, newsVO.getThumb1());  // Folder에서 1건의 파일 삭제
      // System.out.println("sw: " + sw);
      
      file1 = "";
      thumb1 = "";
      size1 = 0;
      
      newsVO.setFile1(file1);
      newsVO.setThumb1(thumb1);
      newsVO.setSize1(size1);
      // -------------------------------------------------------------------
      // 파일 삭제 종료 시작
      // -------------------------------------------------------------------
      
      // mav.addObject("nowPage", nowPage);  // redirect시에 get parameter로 전송됨
      mav.addObject("news_no", news_no);
      mav.setViewName("redirect:/news/read.do");
      
      cnt = this.newsProc.img_delete(newsVO);
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.setViewName("/news/update_msg"); // webapp/news/update_msg.jsp
      
    }
  
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
            
    return mav;    
  }
  
  /**
   * 메인 이미지 수정 처리 http://localhost:9090/team2/news/img_update.do
   * 기존 이미지 삭제후 새로운 이미지 등록(수정 처리)
   * @return
   */
  @RequestMapping(value = "/news/img_update.do", method = RequestMethod.POST)
  public ModelAndView img_update(HttpServletRequest request, 
                                     NewsVO newsVO) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("news_no", newsVO.getNews_no());
    hashMap.put("news_passwd", newsVO.getNews_passwd());
    
    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0;             // 수정된 레코드 갯수 
    
    passwd_cnt = this.newsProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // 패스워드가 일치할 경우 파일 업로드
      // -------------------------------------------------------------------
      // 파일 삭제 코드 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      NewsVO vo = newsProc.read(newsVO.getNews_no());
      // System.out.println("file1: " + contentsVO.getFile1());
      
      String file1 = vo.getFile1().trim();
      String thumb1 = vo.getThumb1().trim();
      long size1 = 0;
      boolean sw = false;
      
      String upDir = Tool.getRealPath(request, "/news/storage/main_images"); // 절대 경로
      sw = Tool.deleteFile(upDir, newsVO.getFile1());  // Folder에서 1건의 파일 삭제
      sw = Tool.deleteFile(upDir, newsVO.getThumb1());  // Folder에서 1건의 파일 삭제
      // System.out.println("sw: " + sw);
      // -------------------------------------------------------------------
      // 파일 삭제 종료 시작
      // -------------------------------------------------------------------
      
      // -------------------------------------------------------------------
      // 파일 전송 코드 시작
      // -------------------------------------------------------------------
      // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택" multiple="multiple">
      MultipartFile mf = newsVO.getFile1MF();
      size1 = mf.getSize();  // 파일 크기
      if (size1 > 0) { // 파일 크기 체크
        // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        file1 = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1, 200, 150); 
        }
      }    
      
      newsVO.setFile1(file1);
      newsVO.setThumb1(thumb1);
      newsVO.setSize1(size1);
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------
  
      // mav.addObject("nowPage", nowPage);
      mav.addObject("news_no", newsVO.getNews_no());
      mav.setViewName("redirect:/news/read.do");
      
      
      cnt = this.newsProc.img_create(newsVO);
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.setViewName("/news/update_msg"); // webapp/news/update_msg.jsp
      
    }
  
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
            
    return mav;    
  }
  
  /**
   * 새로고침을 방지하는 메시지 출력
   * @return
   */
  @RequestMapping(value="/news/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    
    // 등록 처리 메시지: create_msg --> /contents/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /contents/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /contents/delete_msg.jsp
    mav.setViewName("/news/" + url); // forward
    
    return mav; // forward
  }
  
  
  
}