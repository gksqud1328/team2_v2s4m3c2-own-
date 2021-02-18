<%@ page contentType="text/html; charset=UTF-8" %>

<% 
// String root = request.getContextPath(); // /resort 
// ${pageContext.request.contextPath}
%>

<DIV class='container' style='width: 100%;'> 
  <!-- 화면 상단 메뉴 -->
  <DIV class='top_img'>
  <DIV class='top_menu_label'>team2 0.1 전통주 리뷰 커뮤니티</DIV>
</DIV>

  <div id = "menu_main">
    <ul class = "main">
        <li><a href='${pageContext.request.contextPath}' > HOME </a></li>
        <li><a href="#">알림 </a>
          <ul class= "sub">
            <li><a href="${pageContext.request.contextPath}/notice/list.do">공지 사항</a></li>
            <li><a href="${pageContext.request.contextPath}/newsgrp/list_ajax.do">전통주 뉴스</a></li>
            <li><a href="${pageContext.request.contextPath}/survey/list.do">설문조사</a></li>
          </ul>
        </li>
        <li><a href="#">랭킹 </a>
          <ul class= "sub">
            <li><a href="#">주간 랭킹</a></li>
            <li><a href="#">월간 랭킹</a></li>
            <li><a href="#">종류별 랭킹</a></li>
            <li><a href="#">조회수 랭킹</a></li>
          </ul>
        </li>
        <li><a href="#">전통주 </a>
          <ul class= "sub">
            <li><a href="#">탁주</a></li>
            <li><a href="#">맥주&청주</a></li>
            <li><a href="#">과실주</a></li>
            <li><a href="#">증류주</a></li>
            <li><a href="#">리큐르&기타주류</a></li>
          </ul>
        </li>
         <li><a href='#'> 커뮤니티 </a>
          <ul class= "sub">   
            <li><a href="#">자유 게시판</a></li>
            <li><a href="#">술 토크</a></li>
            <li><a href="#">기타 게시판</a></li>
            <li><a href='${pageContext.request.contextPath}/qna/list.do'> Q&A </a></li>
            <li><a href="#">FAQ</a></li>            
          </ul>
        </li>
     </ul>
  </div>
 
  <!-- 화면을 2개로 분할하여 좌측은 메뉴, 우측은 내용으로 구성 -->  
  <DIV class="row" style='margin-top: 2px;' >
    <DIV class="col-sm-3 col-md-2"> <!-- 메뉴 출력 컬럼 -->
      <img src='${pageContext.request.contextPath}/menu/images/alcohol02.png' style='width: 100%;'>
      <div style='margin-top: 5px;'>
        <img src='${pageContext.request.contextPath}/menu/images/alcohol01.jpg' style='width: 20%;'>커뮤니티
      </div>
       ▷ <A href='${pageContext.request.contextPath}/qna/list.do'>Q&A</A>
       <!-- Spring 출력 카테고리 그룹 / 카테고리 -->
      <%-- <c:import url="/cate/list_index_left.do" /> --%>  
    </div>
      
    <DIV class="col-sm-9 col-md-10 cont" >  <!-- 내용 출력 컬럼 -->  
   
<DIV class='content' >


