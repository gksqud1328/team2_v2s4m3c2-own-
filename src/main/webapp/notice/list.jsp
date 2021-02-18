<%@ page contentType="text/html; charset=UTF-8" %>
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
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="/menu/top.jsp" />
 
<DIV class='title_line'><a href="../">홈 </a> > 공지사항</DIV> 
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>    
    <col style='width: 7%;'/>
    <col style='width: 7%;'/>
    <col style='width: 10%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class="th_bs">등록번호</TH>
    <TH class="th_bs">글번호</TH>
    <TH class="th_bs_left">제목</TH>
    <TH class="th_bs">등록일</TH>
    <TH class="th_bs">조회수</TH>
    <TH class="th_bs">출력모드</TH>
    <TH class="th_bs">순서</TH>
  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="noticeVO" items="${list }">
    <c:set var="noticeno" value="${noticeVO.noticeno }"/>
    <TR>  
      <TD class="td_bs_center">${noticeVO.noticeno }</TD>
      <TD class="td_bs_center">${noticeVO.seqno }</TD>
      <TD>
      <a href="./read.do?noticeno=${noticeno }">${noticeVO.head }</a>
      </TD>
      <TD class="td_bs_center">${noticeVO.rdate.substring(0,10) }</TD>
      <TD class="td_bs_center">${noticeVO.count }</TD>
      <TD class="td_bs_center"> 
        <c:choose>
          <c:when test="${noticeVO.visible == 'Y'}">
            <A href="./update_visible.do?noticeno=${noticeno }&visible=${noticeVO.visible }"><IMG src="./images/keypressok.png"></A>
          </c:when>
          <c:otherwise>
            <A href="./update_visible.do?noticeno=${noticeno }&visible=${noticeVO.visible }"><IMG src="./images/keypressno.png"></A>
          </c:otherwise>
        </c:choose>
      </TD> 
      <TD class="td_bs_center">
       <%--  <A href="./update.do?noticeno=${noticeno }"><span class="glyphicon glyphicon-pencil"></span></A>
        <A href="./read_delete.do?noticeno=${noticeno }"><span class="glyphicon glyphicon-trash"></span></A> --%>
        <A href="./update_noticeno_up.do?noticeno=${noticeno }"><span class="glyphicon glyphicon-arrow-up"></span></A>
        <A href="./update_noticeno_down.do?noticeno=${noticeno }"><span class="glyphicon glyphicon-arrow-down"></span></A>         
      </TD>        
    </TR>
  </c:forEach>
  </tbody>

</TABLE>
 <DIV class='content_bottom_menu'>
        <button type="button" 
                    onclick="location.href='./create.do'" 
                    class="btn btn-info">등록</button>
  </DIV>
 
<jsp:include page="/menu/bottom.jsp" />
</body>
 
</html> 
 