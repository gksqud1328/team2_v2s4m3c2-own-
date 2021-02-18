<%@ page contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
  <title>설문 조사</title>
  
  <link href="../css/style.css" rel="stylesheet" type="text/css">  
    
  <script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
           
  <script type="text/javascript">
    window.onload = function() {
      listRequest(); // 목록 호출
    }

    // 팀 그룹별 목록을 가져옴.
    function listRequest() {
      var params = "";   // 서버로 전달하는 파라미터 목록

      $.ajaxSetup({dataType: "text"}); // jQuery, Ajax 요청 설정
      $.post("./list.do", params, listResponse);       
    }
        
    // 응답 처리 함수
    // data: 응답 결과 문자열
    // textStatus: 처리 상태 리턴, success등
    function listResponse(data, textStatus) {
      var result = eval("("+data+")"); // 객체로 변환
                     
      if (result.code == 'success') {
        // 태그의 id가 'listDiv'인 태그 검색, 글 목록 출력 위치
        // var listDiv = document.getElementById("listDiv");
        var listDiv = $("#listDiv");
                
        // 내용으로 공백을 할당하여 태그의 내용 삭제
        listDiv.text('');
                        
        var array = result.array; // 서버로부터의 응답 결과, Array
        // alert('data.length: ' + data.length);
        var str = "<table cellspacing='1' cellpadding='1' border='0' bgcolor='skyblue' align='center' width='100%'>" ;
        str += "<tr>";
        str += "  <th>번호</th><th>설문</th><th>기타</th>";
        str += "</tr>";        
        for (var i = 0 ; i < array.length;  i++) {
          str += "<tr bgcolor='#FFFFFF'>";
          str += "  <td align='center' width='10%'>"+array[i].surveyno+"</td>";
          str += "  <td width='60%' class='td_left'>"+array[i].topic+"</td>";
          str += "  <td width='30%' class='td_center'>";
          str += "    <input type='button' value='참여' onclick='location.href=\"../surveyitem/vote.jsp?surveyno="+array[i].surveyno+"\"' />";
          
          str += "  </td>";
          str += "</tr>";
        }
        str += "</table>";

        // alert('str:' + str);
                
        // HTML 형식으로 값을 지정
        listDiv.html(str);

        // 글 쓰기 폼 clear
        // document.frmCreate.reset();
                        
        } else {  // fail
          alert("에러 발생:" + result.message);
        }
    }
        
    //-----------------------------------------------------------
    // 데이터 추가
    //-----------------------------------------------------------
    function createRequest() {
      var params = $("#frmCreate").serialize();
            
      $.ajaxSetup({dataType: "text"});  // JSON Text
      $.post("./create.do", params, createResponse);    
    }
        
    // 댓글 추가 처리 함수
    // 현재 함수를 ajax.js에서 호출하는 부분 this.callback(this.req);
    function createResponse(data, textStatus) {
      // 객체로 변환
      var result = eval("("+data+")");
                     
      if (result.code == 'success') {
        var frmCreate = $("#frmCreate");
        $("#topic", frmCreate).val('');
        
        $("#divCreate").hide(); // 등록폼 감추기
        listRequest();       // 목록 다시 출력, list.js
      } else {  // fail
        alert("데이터 추가시 에러가 발생 했습니다.\n\n" + result.message);
      }
    }
    //-----------------------------------------------------------
        
    //-----------------------------------------------------------
    // 데이터 읽기
    //-----------------------------------------------------------
    function readRequest(surveyno) {
      var params = "surveyno=" + surveyno;
            
      $.ajaxSetup({dataType: "text"});  // JSON Text
      $.post("./read.do", params, readResponse);    
    }
    
    function readResponse(data, textStatus) {
      // 객체로 변환
      var result = eval("("+data+")");
                       
      if (result.code == 'success') {
        var frmUpdate = $("#frmUpdate");
        
        $("#surveyno", frmUpdate).val(result.surveyno);
        $("#topic", frmUpdate).val(result.topic);
          
        $("#divUpdate").show();
      } else {  // fail
        alert("데이터 읽기 처리 에러\n\n" + result.message);
      }
    }
    //-----------------------------------------------------------
  
    //-----------------------------------------------------------
    // 데이터 변경
    //-----------------------------------------------------------
    function updateRequest() {
      var params = $("#frmUpdate").serialize();
            
      $.ajaxSetup({dataType: "text"});  // JSON Text
      $.post("./update.do", params, updateResponse);    
    }
        
    function updateResponse(data, textStatus) {
      // 객체로 변환
      var result = eval("("+data+")");
                     
      if (result.code == 'success') {
        var frmUpdate = $("#frmUpdate");
        $("#topic", frmCreate).val('');
        
        $("#divUpdate").hide(); 
        listRequest();       // 목록 다시 출력, list.js
      } else {  // fail
        alert("데이터 변경시에 에러가 발생 했습니다.\n\n" + result.message);
      }
    }
    //-----------------------------------------------------------
            
    //-----------------------------------------------------------
    // 관리자 인증 후 데이터 삭제
    //-----------------------------------------------------------
    function deleteRequest(surveyno) {
      var sw = confirm('삭제 하시겠습니까?');
      if (sw == true){
        var params = "surveyno=" + surveyno;
           
        $.ajaxSetup({dataType: "text"});  // JSON Text
        $.post("./delete.do", params, deleteResponse);   
      }else{
        alert('삭제를 취소 했습니다.');
      } 
    }
        
    function deleteResponse(data, textStatus) {
      // 객체로 변환
      var result = eval("("+data+")");
                     
      if (result.code == 'success') {
        alert('삭제 했습니다.');
        listRequest(); // 목록 새로 고침
      } else {  // fail
        alert("삭제 실패\n\n" + result.message);
      }
    }
    //-----------------------------------------------------------
            
    
    // 태그의 값중에 한글 및 특수 문자를 16진수 코드로 변환하여 전송되도록 합니다.
    function ecd(value){
        return encodeURIComponent(value);  // 브러우저 지원 전역 함수
    }

    // 문자열에서 특정 문자의 변환
    function replaceAll(str, searchStr, replaceStr) {

        while (str.indexOf(searchStr) != -1) {
            str = str.replace(searchStr, replaceStr);
        }

        return str;

    }

    // 태그를 보여줌
    function show(elementId) {
        var element = document.getElementById(elementId); // DOM
        if (element) {
            element.style.display = '';  // 태그 출력 
        }
    }
        
    // 태그를 숨김
    function hide(elementId) {
        var element = document.getElementById(elementId);
        if (element) {
            element.style.display = 'none';  // 태그 감춤 
        }
    }
    
    // 태그 출력
    // var element = document.getElementById(elementId); // DOM
    // if (element) {  // 존재하면
    //   element.style.display = '';  // 태그 출력 
    // }
    function show(id){
        $('#' + id).show(); // $('#' + id): id로 태그 검색      
    }    

    // 태그 감추기
    // var element = document.getElementById(elementId); // DOM
    // if (element) {  // 존재하면
    //   element.style.display = 'none';  // 태그 감추기 
    // }        
    function hide(id){
        $('#' + id).hide();       
    }     
         
    </script>

</head>
<!-- *********************************************** -->
<body style="margin: 0px">


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<title>누룽지</title>
      
<link href="/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/menu/dist/css/superfish.css" media="screen">
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script src="/menu/dist/js/hoverIntent.js"></script>
<script src="/menu/dist/js/superfish.js"></script>
    

<script type="text/javascript">
  (function($){ 

    $(document).ready(function(){

      // initialise plugin
      var example = $('#top_nav').superfish({
        //add options here if required
      });

      // buttons to demonstrate Superfish's public methods
      $('.destroy').on('click', function(){
        example.superfish('destroy');
      });

      $('.init').on('click', function(){
        example.superfish();
      });

      $('.open').on('click', function(){
        example.children('li:first').superfish('show');
      });

      $('.close').on('click', function(){
        example.children('li:first').superfish('hide');
      });
    });

  })(jQuery);

</script>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-125981779-1"></script>
<script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    gtag('js', new Date());

    gtag('config', 'UA-125981779-1');
</script>
  
</head>
<body style="margin: 0px">
<DIV class='container' style='width: 100%;'> 
  <!-- 화면 상단 메뉴 -->
  <DIV class='top_img'>
  <DIV class='top_menu_label'>team2 0.1 전통주 리뷰 사이트</DIV>
</DIV>

  <div id = "menu_main">
    <ul class = "main">
        <li><a href='${pageContext.request.contextPath}' > HOME </a></li>
        <li><a href="#">알림 </a>
          <ul class= "sub">
            <li><a href="${pageContext.request.contextPath}/notice/list.do">공지 사항</a></li>
            <li><a href="${pageContext.request.contextPath}/news/list.do">전통주 뉴스</a></li>
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



<!-- ********** 관리자 메뉴 ********** -->
  
  
  
</ul>
  </div>



<DIV style="text-align: center; clear: both;">






<!-- *********************************************** -->
<br><br>

<table  width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
<tr>
  <td align="center">
    <b>설문 조사</b>
    [
    
    <a href="javascript:listRequest();">새로 고침</a> | 
    <a href="javascript:location.href='../mail/mail_form.jsp';">비공개 메일 보내기</a>
    ]
    <br><br>   
  </td>
</tr>

<tr>
<td>
<!-- *************** 글 등록 폼 시작 *************** -->
<div id="divCreate" style="display:none;">
<form id="frmCreate" name="frmCreate" >
<table width="80%" border="0" cellspacing="1" cellpadding="1" 
       align="center">
  <tr>
    <td bgcolor="#EEEEFF">등록할 설문 제목</td>
    <td align="left">
      <input type="text" name="topic" id="topic" size="50" value="" />
      
      [<a href="javascript:createRequest()">등록</a>]
      [<a href="javascript:reset(document.frmCreate)">다시쓰기</a>]
      [<a href="javascript:hide('divCreate')">등록 취소</a>]
    </td>
  </tr>
</table>
</form>
</div>
<!-- *************** 글 등록 폼 종료*************** -->



<!-- *************** 글 수정 폼 시작 *************** -->
<div id="divUpdate" style="display:none;">
<form id="frmUpdate" name="frmUpdate">
<input type="hidden" name="surveyno" id="surveyno" value="">

<table width="80%" border="0" cellspacing="1" cellpadding="1" 
       align="center">
  <tr>
    <td bgcolor="#EEEEFF">변경할 설문 제목</td>
    <td align="left">
      <input type="text" name="topic" id="topic" size="50" value="" />
      [<a href="javascript:updateRequest()">저장</a>]
      [<a href="javascript:reset(document.frmCreate)">다시쓰기</a>]
      [<a href="javascript:hide('divUpdate')">변경 취소</a>]
    </td>
  </tr>
</table>

</form>
</div>
<!-- *************** 글 수정 폼 종료 *************** -->

<br>
<!-- *************** 글 목록 출력 시작 *************** -->
<div id="listDiv" width="100%"></div>
<!-- *************** 글 목록 출력 끝   *************** -->
</td>
</tr>

</table>

<!-- *********************************************** -->


</DIV>

<div style="font-size: 12px; text-align: center; 
     background-color: #BDD2FF; color: #13a; width: 100%; font: 굴림체; padding: 2px; font-weight: bold; margin-top: 30px;">
  <span>Copyright 솔데스크 All rights reserved</span>
</div>
</body>
</html>


<!-- *********************************************** -->



    
    

