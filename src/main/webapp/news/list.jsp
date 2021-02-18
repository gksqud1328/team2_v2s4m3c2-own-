<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>전통주 리뷰 커뮤니티</title>
 
 <link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
  <DIV class="title_line">
    ${newsVO.newsgrp_head}
  </DIV>
  <ASIDE class="aside_left">
    <A href="../"> 홈 </A> > <A href="../newsgrp/list_ajax.do"> 뉴스 그룹</A> > ${newsgrpVO.newsgrp_head } 전체보기(목록형)
  </ASIDE>
  <ASIDE class="aside_right">
    <A href="./create.do?newsgrp_no=${param.newsgrp_no }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_newsgrp_no_grid1.do?newsgrp_no=${param.newsgrp_no }">갤러리형</A>     
  </ASIDE> 
  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'>
    <TABLE class='table table-striped'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 20%;'/>
      <col style='width: 40%;'/>
      <col style='width: 20%;'/>
    </colgroup>
 
  <thead>  
    <TR>
     <TH class="th_bs">등록번호</TH>
     <TH class="th_bs_center">파일</TH>
     <TH class="th_bs">제목</TH>
     <TH class="th_bs">등록일</TH>
     <TH class="th_bs">조회수</TH>
   </TR>
  </thead>
      <!-- table -->
      <c:forEach var="newsVO" items="${list }">
        <c:set var="news_no" value="${newsVO.news_no }" />
        <c:set var="thumb1" value="${newsVO.thumb1 }" />
        <c:set var="news_head" value="${newsVO.news_head}" />
        <c:set var="news_date" value="${newsVO.news_date}" />
        <c:set var="news_count" value="${newsVO.news_count}" />
        <c:set var="file1" value="${newsVO.file1}" />
        <c:set var="size1" value="${newsVO.size1}" />
        
        <tr> 
            <td style='vertical-align: middle; text-align: center;'>${newsVO.news_no}</td>
            <td>
              <c:choose>
                <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                  <IMG src="./storage/main_images/${thumb1 }" style="width: 120px; height: 80px;"> 
                </c:when>
                <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                  ${file1}
                </c:otherwise>
              </c:choose>
            </td>
            <td style='vertical-align: middle; text-align: left;'>
            <a href="./read.do?news_no=${news_no}&newsgrp_no=${param.newsgrp_no}">${newsVO.news_head}</a></td>
            <td style='vertical-align: middle; text-align: left;'>${news_date.substring(0, 10)}</td> 
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <DIV class='bottom_menu'>${paging }</DIV>
  </div>
 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>
