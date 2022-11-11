<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>withdraw.jsp</h1>
<h2>출금이체</h2>

${withdrawOK }

	<form method="post" action="transferResult" id="fr">
<%-- 		<input type="hidden" name="access_token" value="${responseToken.access_token }"> --%>
		<input type="hidden" name="access_token" value="${access_token }">
<!-- 		<input type="hidden" name="access_token" value="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDE0NzM5Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NzU2NDQ0MDQsImp0aSI6ImY1MjRlZTNhLWM4OGQtNDU2Mi1iMDUxLWVkZmVkMjEzMTg3MCJ9.nIKZNu6hVXVvCtp3TDmX3Yvs7BJoA8f-2KYpulE7Zu8"> -->
		
		<input type="hidden" name="check_type" value="1">
		<input type="hidden" name="tran_dtime" value="20221104101921">
		<input type="hidden" name="req_cnt" value="1">
		<input type="hidden" name="req_list" value="???">
		
		
		<input type="hidden" name="tran_no" value="1">
		<input type="hidden" name="org_bank_tran_id" value="M202202083O4BC342366">
		
		<input type="hidden" name="org_bank_tran_date" value="20221104101921">
		<input type="hidden" name="org_tran_amt" value="000000001000">
		
		<input type="submit" value="이체내역확인" id="clickTest">	
	</form>





</body>
</html>