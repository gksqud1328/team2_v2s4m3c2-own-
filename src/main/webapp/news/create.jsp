<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>전통주 리뷰 커뮤니티</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- <script type="text/javascript" src="../ckeditor/ckeditor.js"></script> -->
 
<!-- <script type="text/JavaScript">
  // window.onload=function(){
  //  CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  // };

  $(function() {
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  });
 
</script> -->

</head> 
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
  <DIV class="title_line">
    ${newsVO.news_head}
  </DIV>
  
  <ASIDE class="aside_left">
    <A href="../"> 홈 </A> > <A href="../newsgrp/list_ajax.do">뉴스 그룹</A> > 
    <A href="../news/list_by_newsgrp_no_grid1.do?newsgrp_no=${newsgrpVO.newsgrp_no }">
    ${newsgrpVO.newsgrp_head }</A> > ${newsgrpVO.newsgrp_head }글 목록 > 등록
  </ASIDE> 
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
  
  <DIV style='width: 100%;'>
    <FORM name='frm' method='POST' action='./create.do' class="form-horizontal"
                enctype="multipart/form-data">
               
      <!-- FK newsgrp_no 지정 -->
      <input type='hidden' name='newsgrp_no' id='newsgrp_no' value="${param.newsgrp_no }">
      <%-- <input type='hidden' name='news_no' id='news_no' value="${param.news_no }"> --%>
      
      <div class="form-group">   
        <div class="col-md-12">
          <input type='text' class="form-control" name='news_head' value="제목" placeholder="제목" required="required" style='width: 80%;'>
        </div>
      </div>   
      
      <div class="form-group">   
        <div class="col-md-12">
          <textarea class="form-control" name='news_content' id='news_content' rows='10' placeholder="내용">겨울</textarea>
        </div>
      </div>

      <div class="form-group">   
        <div class="col-md-12">
          실제 컬럼명: file1, Spring File 객체 대응: file1MF 
          <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
        </div>
      </div>

      <div class="form-group">   
        <div class="col-md-12">
          <input type='password' class="form-control" name='news_passwd'  value='1234' placeholder="패스워드" style='width: 20%;'>
        </div>
      </div>
      
      <DIV class='content_bottom_menu'>
        <button type="submit" class="btn btn-info">등록</button>
        <button type="button" 
                    onclick="location.href='./list_by_newsgrp_no_grid1.do?newsgrp_no=${param.newsgrp_no}'" 
                    class="btn btn-info">취소[목록]</button>
      </DIV>
       
    </FORM>
  </DIV>

  
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>
 
  