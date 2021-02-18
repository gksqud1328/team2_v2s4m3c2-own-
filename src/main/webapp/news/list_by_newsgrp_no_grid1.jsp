<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
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
<jsp:include page="/menu/top.jsp" flush='false' />
  <DIV class="title_line">
    ${newsVO.newsgrp_head}
  </DIV>
  <ASIDE class="aside_left">
    <A href="../"> 홈 </A> > <A href="../newsgrp/list_ajax.do"> 뉴스 그룹</A> > ${param.newsgrp_head } 전체보기(갤러리형)
  </ASIDE>
  <ASIDE class="aside_right">
    <A href="./create.do?newsgrp_no=${param.newsgrp_no }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list.do?newsgrp_no=${param.newsgrp_no }">목록형</A>     
  </ASIDE> 
  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'>
    <!-- 갤러리 Layout 시작 -->
    <c:forEach var="newsVO" items="${list }" varStatus="status">
      <c:set var="news_no" value="${newsVO.news_no }" />
      <c:set var="thumb1" value="${newsVO.thumb1 }" />
      <c:set var="title" value="${newsVO.news_head}" />
      <c:set var="rdate" value="${newsVO.news_date}" />
      <c:set var="file1" value="${newsVO.file1}" />
      <c:set var="size1" value="${newsVO.size1}" />

      <%--하나의 행에 이미지를 4개씩 출력후 행 변경 --%>
      <c:if test="${status.index % 4 == 0 && status.index != 0 }"> 
        <HR class='menu_line'>
      </c:if>
      
      <!-- 하나의 이미지, 24 * 4 = 96% -->
      <DIV style='width: 24%; 
              float: left; 
              margin: 0.5%; padding: 0.5%; background-color: #EEEFFF;'>
        <c:choose>
          <c:when test="${size1 > 0}"> <!-- 파일이 존재하면 -->
            <c:choose> 
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <!-- 이미지 인경우 -->
                <a href="./read.do?news_no=${news_no}&newsgrp_no=${param.newsgrp_no}">               
                  <IMG src="./storage/main_images/${thumb1 }" style='width: 100%; height: 150px;'>
                </a><br>
                ${newsVO.news_head}
              </c:when>
              <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                <DIV style='width: 100%; height: 150px; display: table; border: solid 1px #CCCCCC;'>
                  <DIV style='display: table-cell; vertical-align: middle; text-align: center;'> <!-- 수직 가운데 정렬 -->
                    <a href="./read.do?news_no=${news_no}&newsgrp_no=${param.newsgrp_no}">${newsVO.news_no}</a><br>
                  </DIV>
                </DIV>
                ${newsVO.news_head}           
              </c:otherwise>
            </c:choose>
          </c:when>
          <c:otherwise> <%-- 파일이 없는 경우 기본 이미지 출력 --%>
            <a href="./read.do?news_no=${news_no}&newsgrp_no=${param.newsgrp_no}">
              <img src='./images/none1.png' style='width: 100%; height: 150px;'>
            </a><br>
            이미지를 등록해주세요.
          </c:otherwise>
        </c:choose>         
      </DIV>  
    </c:forEach>
    <!-- 갤러리 Layout 종료 -->
    <br><br>
  </div>
 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>


