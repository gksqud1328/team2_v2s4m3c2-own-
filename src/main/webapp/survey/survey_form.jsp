<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>전통주 리뷰 커뮤니티</title>
<script type="text/JavaScript" 
        src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head> 
<body>
  <form name='frm' action='./survey_proc.jsp' method='POST'>
    
     1. 귀하의 성별은 무엇입니까?
     <br><br>
     <label style="cursor: pointer;">
       <input type="radio" name="gender" value="남">남
     </label>
     <label style="cursor: pointer;">
       <input type="radio" name="gender" value="여">여
     </label>
     <br><br>
     2. 저희 홈페이지를 어떻게 찾아 오시게 되었습니까?
     <br><br>
     <label style="cursor: pointer;">
       <input type="radio" name="finder" value="지인 소개를 통해서">지인 소개를 통해서
     </label>
     <label style="cursor: pointer;">
       <input type="radio" name="finder" value="블로그 소개를 통해서">블로그 소개를 통해서
     </label>
     <label style="cursor: pointer;">
       <input type="radio" name="finder" value="우연히 찾아오게 되었다">우연히 찾아오게 되었다
     </label>
     <br><br>    
     3. 홈페이지 사용에 불편한 점이 있습니까? 있다면 아래 적어주시기 바랍니다.
     <br><br>
     <label style="cursor: pointer;">
       <input type="radio" name="improve" value="없음">없음
     </label>     
     <label style="cursor: pointer;">
       <input type="radio" name="improve" value="모르겠음">잘 모르겠음
     </label>     
     <label style="cursor: pointer;">
       <input type="radio" name="improve" value="있음">있음
     </label>     
     <br><br>
     3-1. "있음" 을 체크한 경우에만 작성. <br>
     <textarea class="form-control" name='' id='' cols='5' rows='20'  placeholder="내용">내용</textarea>        
     <br><br>
     <button type="submit">제출</button> 
  </form>
</body>
 
</html>

