<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 정보조회 결과</h1>
	<h3>고객번호 : ${userInfo.user_seq_no }</h3>
	<h3>고객CI값 : ${userInfo.user_ci }</h3>
	<h3>res_list : ${userInfo.res_list} </h3>
	
	<%-- accountList 객체에 저장되어 있는 계좌 목록(res_list) 가져와서 반복하여 복수개 계좌 접근 --%>
		<c:forEach var="account" items="${userInfo.res_list }">
			<tr>
				<td>${account.fintech_use_num }</td>
				<td>${account.account_num_masked }</td>
				<td>${account.bank_code_std }</td>
				<td>${account.bank_name }</td>
				<td>${account.account_num }</td>
				<td>${account.account_holder_name }</td>
			</tr>
		</c:forEach>
	
	
	
</body>
</html>














