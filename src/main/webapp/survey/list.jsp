<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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

<DIV class='title_line'><a href="../">홈 </a> > 설문조사</DIV> 
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 30%;'/>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>    
    <col style='width: 15%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class="th_bs">번호</TH>
    <TH class="th_bs">제목</TH>
    <TH class="th_bs">등록일</TH>
    <TH class="th_bs">조회수</TH>
    <TH class="th_bs">기타</TH>

  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="surveyVO" items="${list }">
    <c:set var="survey_no" value="${surveyVO.survey_no }"/>
    <TR>  
      <TD class="td_bs_left">${surveyVO.survey_no }</TD>
      <TD class="td_bs_left">
      <a href="./read.do?survey_no=${survey_no }">${surveyVO.survey_head }</a>
      </TD>
      <TD class="td_bs_left">${surveyVO.rdate.substring(0, 11)}</TD>
      <TD class="td_bs_left">${surveyVO.survey_count }</TD>
      <TD class="td_bs_left">
        <A href="../survey_item/create">참여</A>
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
 