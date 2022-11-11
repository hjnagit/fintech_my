<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#clickTest').on("click", function(){
			alert('zmfflr');
//			let info = $('#fr');
// 			$.ajax({
// 				type : "POST",
// 				url : "withdraw",
// 				async : true,
// 				data : JSON.stringify(info),
// 				dataType : "json",
// 				contentType : "application/json;charset=UTF-8",
// 				success : function(data) {
// 					alert('성공'+data);
// 				},
// 				error : function(error) {
// 					console.log(error);
// 				}
// 			});




		});

	});
	
	function test(){
		alert('tlfdle');
	}
	
</script>


</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<!-- 인증 요청 작업 수행 -->
<form action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" method="get">
<input type="hidden" name="response_type" value="code">
<input type="hidden" name="client_id" value="6f948a13-11af-4892-b746-ee67d358abf2">
<input type="hidden" name="redirect_uri" value="http://localhost:8088/fintech/callback">
<input type="hidden" name="scope" value="login inquiry transfer oob">
<input type="hidden" name="state" value="12345678123456781234567812345678">
<input type="hidden" name="auth_type" value="0">

<input type="submit" value="토큰발급">
</form>


<!-- <form action="accountCancel" method="post">
<input type="hidden" name="access_token" value="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDE0NzM5Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NzU0OTkzMzMsImp0aSI6IjdhNTIzMjNhLWIzMTctNDM2Mi05ZjE1LTk3NDdjNjY1ZmY3NCJ9.HwrZtShpYyLHlGixneAWJF7S-Y92106u-9GvJp5Au1Q">
<input type="hidden" name="bank_tran_id" value="1101014739O4BC34239Z">
<input type="hidden" name="scope" value="inquiry transfer">
<input type="hidden" name="fintech_use_num" value="120220208388941285310465">
<input type="submit" value="계좌해지">

</form>


<form action="getTokenOne" method="post">
<input type="hidden" name="code" value="DMNhvWiS9BRmHdwCYIDlswXqkQC9Dk">
<input type="hidden" name="client_id" value="6f948a13-11af-4892-b746-ee67d358abf2">
<input type="hidden" name="client_secret" value="4008271b-939d-4ee9-a981-5a132490eafc">
<input type="hidden" name="redirect_uri" value="http://localhost:8088/fintech/callback">
<input type="hidden" name="grant_type" value="authorization_code">

<input type="submit" value="토큰만발급">
</form>
 -->
<input type="button" value="출금이체 슝 갑니다" id="clickTest">
</body>
</html>



