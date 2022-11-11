<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>토큰만 받아오기</h1>

<h2>액세스 토큰 : ${responseToken.access_token }</h2>
<h2>사용자 번호 : ${responseToken.user_seq_no }</h2>

<h3>${responseToken}</h3>


</body>
</html>