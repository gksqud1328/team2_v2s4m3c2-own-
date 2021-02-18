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
  <div style='width: 100%; float: left;  background-color: #BDD2FF; height: 25px; margin-bottom:1px;  border: none;'>
   <ul class="sf-menu" id="top_nav" style='position: absolute; top: 0px; left: 0px; border: none'> 
  
   
    <li><a href='/'>H</a></li> 
    <li><a href='/member/logout.do'>ai7 Logout</a></li>   
  

  
      <li>
        <A href='#'>Com</A>
        <UL> 
               
            <li><a href='/pds/list.jsp?pdsgrpno=10'>Web/프로그램 개발 자료실</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=141'>데이터 분석/Machine Learning 개발 자료실</a></li>
             
            <li><a href='/bbs/list.jsp?pdsgrpno=32&memberid=com'>프로젝트 질문/답변</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=40&memberid=com'>AI7 과제</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=42&memberid=com'>AI7 프로젝트</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=41&memberid=com'>AI7 평가 시험 등록</a></li>
          
        </UL>
    </li>
    
      <li>
        <A href='#'>AI7</A>
        <UL> 
               
            <li><a href='/pds/list.jsp?pdsgrpno=339'>공지사항</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=340'>프로그래밍 언어활용(JAVA)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=349'>화면 설계/화면구현 1(HTML5/CSS3/JSP)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=354'>서버 프로그램 구현 1(Spring/MyBATIS/Maven)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=369'>서버 프로그램 구현 2(Spring Boot)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=356'>화면구현 2(Bootstrap/JavaScript/Ajax/JSON/jQuery)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=351'>데이터베이스 요구사항 분석/SQL 응용(Oracle)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=368'>빅데이터 플랫폼 요구사항 분석/빅데이터 플랫폼 아키텍처 설계</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=361'>Python and Web 크롤링(Python)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=370'>R</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=371'>기술 통계 기반 데이터 분석 및 시각화(Analysis, R)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=362'>추론 통계 기반 데이터 분석/머신러닝 기초 알고리즘/머신러닝 기반 데이터 분석(Machine, R)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=363'>텐서플로 활용한 머신러닝/딥러닝(Tensorflow, Python, Django)</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=367'>애플리케이션 테스트 수행</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=366'>통합 구현</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=353'>요구사항 확인/애플리케이션 요구사항 분석/애플리케이션 설계/Project</a></li>
                
            <li><a href='/pds/list.jsp?pdsgrpno=365'>정보시스템 이행(Ubuntu Linux, Cafe24, AWS EC2)</a></li>
             
            <li><a href='/bbs/list.jsp?pdsgrpno=32&memberid=ai7'>프로젝트 질문/답변</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=40&memberid=ai7'>AI7 과제</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=42&memberid=ai7'>AI7 프로젝트</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=41&memberid=ai7'>AI7 평가 시험 등록</a></li>
          
        </UL>
    </li>
    
      <li>
        <A href='#'>Online</A>
        <UL> 
            
            <li><a href='/bbs/list.jsp?pdsgrpno=32&memberid=online'>프로젝트 질문/답변</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=40&memberid=online'>AI7 과제</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=42&memberid=online'>AI7 프로젝트</a></li>
            
            <li><a href='/bbs/list.jsp?pdsgrpno=41&memberid=online'>AI7 평가 시험 등록</a></li>
          
        </UL>
    </li>
        
    <li>
      <A href='#'>ETC</A>
      <ul>
        <li><a href='/survey/survey.jsp'>Survey</a></li>
        <li><a href='/mail/mail_form.jsp'>Request</a></li>
        <li><a href='/cbt/index.jsp' target='_blank'>CBT</a></li>
        <li><a href='/menu/lectureinfo.jsp'>Lecture</a></li>
        <li><a href='http://www.nulunggi.pe.kr' target='_blank'>Domain</a></li>
      </ul>
    </li>




<!-- ********** 관리자 메뉴 ********** -->


  
  <li>
    <a href='' target='_blank'>New</a>
  </li>
  <li>
    <a href='http://172.16.12.101:9090/ojt/index.jsp' target='_blank'>IN</a>
  </li>  
  <li>
    <a href='https://remotedesktop.google.com/support' target='_blank'>RE</a>
  </li>  
  <li>
    <a href='/srcboard/read.jsp' target='_blank'>SRC</a>
  </li>  
  
  
  
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



    
    

