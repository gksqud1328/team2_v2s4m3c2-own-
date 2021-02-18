<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 글 + 이미지 조회하는 경우 참고  --%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>전통주 리뷰 커뮤니티</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
<c:set var="newsgrp_no" value="${newsgrpVO.newsgrp_no}" />
<c:set var="news_no" value="${newsVO.news_no }" />

<jsp:include page="/menu/top.jsp" flush='false' />
  <DIV class="title_line">
    ${newsVO.news_head}
  </DIV>
  <ASIDE class="aside_left">
    <A href="../"> 홈 </A> > <A href="../newsgrp/list_ajax.do"> 뉴스 그룹</A> > 
    <A href="../news/list_by_newsgrp_no_grid1.do?newsgrp_no=${newsgrpVO.newsgrp_no }">${newsgrpVO.newsgrp_head }</A> > ${newsVO.news_head }
  </ASIDE>
  <ASIDE class="aside_right">
    <A href="./create.do">글 등록</A>
    <c:choose>
      <c:when test="${newsVO.file1.trim().length() > 0 }">
        <span class='menu_divide' > | </span> 
        <A href='./img_update.do?newsgrp_no=${newsgrp_no }&news_no=${news_no}'>메인 이미지 변경/삭제</A>     
      </c:when>
      <c:otherwise>
        <span class='menu_divide' > | </span> 
        <A href='./img_create.do?newsgrp_no=${newsgrp_no }&news_no=${news_no}'>메인 이미지 등록</A>     
      </c:otherwise>
    </c:choose>    
    <span class='menu_divide' > | </span>
    <A href='../attachfile/create.do?news_no=${news_no }&newsgrp_no=${newsgrp_no }'>파일 등록</A>
    <span class='menu_divide' > | </span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' > | </span> 
    <A href='./list.do?newsgrp_no=${newsgrp_no }'>목록</A>
    <span class='menu_divide' > | </span> 
    <A href='./update.do?newsgrp_no=${newsgrp_no }&news_no=${news_no}'>수정</A>
    <span class='menu_divide' > | </span> 
    <A href='./delete.do?newsgrp_no=${newsgrp_no }&news_no=${news_no}'>삭제</A>
    
  </ASIDE> 
  
  <div class='menu_line'></div>

  <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="news_no" value="${news_no}">
      <fieldset class="fieldset">
        <ul>
          <li class="li_none" style='border-bottom: solid 1px #AAAAAA;'>
            <span>${newsVO.news_head}</span>
            <span>${newsVO.news_date.substring(0, 16)}</span>
          </li>
          <li class="li_none">
            <c:set var="file1" value="${newsVO.file1.toLowerCase() }" />
            <c:if test="${file1.endsWith('jpg') || file1.endsWith('png') || file1.endsWith('gif')}">
              <DIV style="width: 50%; float: left; margin-right: 10px;">
                <IMG src="./storage/main_images/${newsVO.file1 }" style="width: 100%;">
              </DIV> 
            </c:if> 
            <DIV>${newsVO.news_content }</DIV>
          </li>
        </ul>
      </fieldset>
  </FORM>

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>

