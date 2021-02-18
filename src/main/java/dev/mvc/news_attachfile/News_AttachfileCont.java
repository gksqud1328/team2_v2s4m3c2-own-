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
   * 등록 폼 http://localhost:9090/news/attachfile/create.do X
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
   * 등록 처리
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
    // 파일 전송 코드 시작
    // ---------------------------------------------------------------
    int news_no = news_AttachfileVO.getNews_no(); // 부모글 번호
    String news_attachfile_rname = ""; // 원본 파일명
    String news_attachfile_upname = ""; // 업로드된 파일명
    long news_attachfile_size = 0; // 파일 사이즈
    String news_attachfile_thumb = ""; // Preview 이미지
    int upload_count = 0; // 정상처리된 레코드 갯수

    String upDir = Tool.getRealPath(request, "/news_attachfile/storage");

    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    List<MultipartFile> news_attachfile_rnamesMF = news_AttachfileVO.getNews_attachfile_rnamesMF();

    int count = news_attachfile_rnamesMF.size(); // 전송 파일 갯수
    if (count > 0) {
      for (MultipartFile multipartFile : news_attachfile_rnamesMF) { // 파일 추출, 1개이상 파일 처리
        news_attachfile_size = multipartFile.getSize(); // 파일 크기
        if (news_attachfile_size > 0) { // 파일 크기 체크
          news_attachfile_rname = multipartFile.getOriginalFilename(); // 원본 파일명
          news_attachfile_upname = Upload.saveFileSpring(multipartFile, upDir); // 파일 저장, 업로드된 파일명

          if (Tool.isImage(news_attachfile_rname)) { // 이미지인지 검사
            news_attachfile_thumb = Tool.preview(upDir, news_attachfile_upname, 200, 150); // thumb 이미지 생성
          }
        }
        News_AttachfileVO vo = new News_AttachfileVO();
        vo.setNews_no(news_no);
        vo.setNews_attachfile_rname(news_attachfile_rname);
        vo.setNews_attachfile_upname(news_attachfile_upname);
        vo.setNews_attachfile_thumb(news_attachfile_thumb);
        vo.setNews_attachfile_size(news_attachfile_size);

        // 파일 1건 등록 정보 dbms 저장, 파일이 20개이면 20개의 record insert.
        upload_count = upload_count + news_attachfileProc.create(vo);
      }
    }
    // -----------------------------------------------------
    // 파일 전송 코드 종료
    // -----------------------------------------------------

    mav.addObject("news_no", news_no); // redirect parameter 적용
    mav.addObject("newsgrp_no", newsgrp_no); // redirect parameter 적용
    mav.addObject("upload_count", upload_count); // redirect parameter 적용
    mav.addObject("url", "create_msg"); // create_msg.jsp, redirect parameter 적용
    mav.setViewName("redirect:/news_attachfile/msg.do"); // 새로고침 방지
    return mav;
  }

   /**
   * 새로고침을 방지하는 메시지 출력
   * 
   * @param memberno
   * @return
   */
  @RequestMapping(value = "/news_attachfile/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    // 등록 처리 메시지: create_msg --> /attachfile/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /attachfile/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /attachfile/delete_msg.jsp
    mav.setViewName("/news_attachfile/" + url); // forward
    //mav.setViewName("/news_attachfile/create_msg"); // forward

    return mav; // forward
  }
  
   /**
   * 목록 http://localhost:9090/team2/news_attachfile/list.do
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
   * 하나의 news_no별 목록 
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
   * 첨부 파일 1건 삭제 처리
   * 
   * @return
   */
  @RequestMapping(value = "/news_attachfile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(HttpServletRequest request, int news_attachfile_no,
    @RequestParam(value = "news_no", defaultValue = "0") int news_no, String rurl) {
    ModelAndView mav = new ModelAndView();

    // 삭제할 파일 정보를 읽어옴.
    News_AttachfileVO news_AttachfileVO = news_attachfileProc.read(news_attachfile_no);

    String upDir = Tool.getRealPath(request, "/news_attachfile/storage"); // 절대 경로
    Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_rname()); // Folder에서 1건의 파일 삭제
    Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_thumb()); // 1건의 Thumb 파일 삭제

    // DBMS에서 1건의 파일 삭제
    news_attachfileProc.delete(news_attachfile_no);

    List<News_AttachfileVO> list = news_attachfileProc.list(); // 목록 새로 고침
    mav.addObject("list", list);

    mav.addObject("news_no", news_no);

    mav.setViewName("redirect:/news_attachfile/" + rurl);

    return mav;
  }
  

 /**
  * FK를 사용한 레코드 삭제
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
   int cnt = 0; // 삭제된 레코드 갯수

   String upDir = Tool.getRealPath(request, "/news_attachfile/storage"); // 절대 경로
   
   for (News_AttachfileVO news_AttachfileVO: list) { // 파일 갯수만큼 순환
     Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_rname()); // Folder에서 1건의 파일 삭제
     Tool.deleteFile(upDir, news_AttachfileVO.getNews_attachfile_thumb()); // 1건의 Thumb 파일 삭제
   
     news_attachfileProc.delete(news_AttachfileVO.getNews_attachfile_no());  // DBMS에서 1건의 파일 삭제
     cnt = cnt + 1;

   }
   
   JSONObject json = new JSONObject();
   json.put("cnt", cnt);

   return json.toString();
 }

  
  /**
  * 부모키별 갯수 산출
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
