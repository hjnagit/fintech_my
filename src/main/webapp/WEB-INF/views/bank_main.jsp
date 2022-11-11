<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/jquery.serializeObject.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#clickTest').on("click", function(){
			//alert('zmfflr');
			//let info = $('#fr').serializeArray();
			let info = $('#fr').serializeObject();
			let infoD = $('#fr22').serializeObject();
			alert('실행');
			$.ajax({
				url  : "withdraw",
				type : "POST",
				async : true,
				data : JSON.stringify(info),
				dataType:"json",
				contentType : "application/json;charset=UTF-8",
				success 	: function(data) {
					alert("출금성공");
					//$('#result').append(data);
					
					$.ajax({
						url  : "deposit",
						type : "POST",
						async : true,
						data : JSON.stringify(infoD),
						
						dataType:"json",
						contentType : "application/json;charset=UTF-8",
						success 	: function(data) {
							alert("이체가 완료되었습니다.");
							alert(data.api_tran_id + "/" + data.res_list[0]);
							
							$('#result').append(data.api_tran_id + "/" + data.res_list[0].account_holder_name+"님에게 " + data.res_list[0].tran_amt + "원 이체를 완료하셨습니다.");
							
// 							let re = {
// 									prod_num : 12345,
// 									price : data.res_list[0].tran_amt,
// 									payment : "계좌이체"
// 							};
// 							$.ajax({
// 								url  : "payInsert",
// 								type : "POST",
// 								async : true,
// 								data : JSON.stringify(re),
// 								dataType:"json",
// 								contentType : "application/json;charset=UTF-8",
								
// 								success 	: function(data) {
// 									alert('디비저장성공');
// 								},
// 							    error		: function(error) {
// 							    	console.log(error);
// 							    	alert('디비저장실패');
// 							    }
// 							});
							
							
					    },
					    error		: function(error) {
					    	console.log(error);
					    	alert('이체에 실패하셨습니다.');
					    }
					});
					
					
					
					
					
			    },
			    error		: function(error) {
			    	console.log(error);
			    	alert('이체에 실패하셨습니다.');
			    }
			});
			

		})
		
		

 	});
</script>


</head>
<body>
	<h1>bank_main.jsp</h1>
	<h3>인증완료</h3>
	<h2>액세스 토큰 : ${responseToken.access_token }</h2>
	<h2>사용자 번호 : ${responseToken.user_seq_no }</h2>
	<h2>token_type : ${responseToken.token_type }</h2>
	<h2>expires_in : ${responseToken.expires_in }</h2>
	<h2>refresh_token : ${responseToken.refresh_token }</h2>
	<h2>scope : ${responseToken.scope }</h2>


	<hr>

	<form method="get" action="userInfo">
		<%-- 필요 파라미터는 입력데이터 없이 hidden 속성으로 전달 --%>
		<input type="hidden" name="access_token" value="${responseToken.access_token }"> 
		<input type="hidden" name="user_seq_no" value="${responseToken.user_seq_no }">
		<input type="submit" value="사용자정보조회">
	</form>
	<hr>
	<!-- 2.2.3 등록계좌조회 API -->
	<form method="get" action="accountList">
		<%-- 필요 파라미터는 입력데이터 없이 hidden 속성으로 전달 --%>
<%-- 		<input type="hidden" name="access_token" value="${responseToken.access_token }">  --%>
		<input type="hidden" name="access_token" value="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDE0NzM5Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NzU3NTEwNTMsImp0aSI6Ijg4ODQ2MjM0LTMzYzEtNGNiOC04YTIyLTVkYWM4ZTQzNmIxOSJ9.cS_8hboFILyCN-P2KJm2J_PhckkYaxPmDTR2XG7shC0"> 
<%-- 		<input type="hidden" name="user_seq_no" value="${responseToken.user_seq_no }">  --%>
		<input type="hidden" name="user_seq_no" value="1101014739"> 
		<input type="hidden" name="include_cancel_yn" value="Y"> 
		<input type="hidden" name="sort_order" value="D"> 
		<input type="submit" value="등록계좌조회">
	</form>


<%-- 	<form action="accountCancel" method="post">
		<input type="hidden" name="access_token" value="${sessionScope.token }">
		<input type="hidden" name="bank_tran_id" value="M202202083O4BC34237Z">
		<input type="hidden" name="scope" value="inquiry transfer"> 
		<input type="hidden" name="fintech_use_num" value="120220208388941285310465">
		<input type="submit" value="계좌해지">

	</form> --%>
	
		<h2>출금이체 갑니다😁👌😁👌   😀😀😀😀        </h2>
	<form method="post" action="withdraw" id="fr">
		<input type="hidden" name="access_token" value="${responseToken.access_token }">
<%-- 		<input type="hidden" name="access_token" value="${sessionScope.token }"> --%>
<!-- 		<input type="hidden" name="access_token" value="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDE0NzM5Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwib29iIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NzU4MTc5ODAsImp0aSI6IjkyNWY0YmYzLWQ3Y2MtNDNjOC05MGE4LTM5NjRmMmM3MWMzZCJ9.bt9d3Q_mKLxhtnLxQEJ3omtxYJSvVlex1GAje7Ver5Q"> -->
		
<!-- 		<input type="hidden" name="bank_tran_id" value="M202202083U7BC366365"> -->
		<input type="hidden" name="cntr_account_type" value="N">
		<input type="hidden" name="cntr_account_num" value="100000000001">
		<input type="hidden" name="dps_print_content" value="홍길동">
		<input type="hidden" name="fintech_use_num" value="120220208388941285547548">
<!-- 		<input type="hidden" name="fintech_use_num" value="120220208388941285310465"> -->
<!-- 		<input type="hidden" name="wd_print_content" value="오픈뱅킹출금"> -->
		
		<input type="hidden" name="tran_amt" value="2000">
		<input type="hidden" name="tran_dtime" value="20221104101921">
		<input type="hidden" name="req_client_name" value="홍길동">
<!-- 		<input type="hidden" name="req_client_bank_code" value="002"> -->
<!-- 		<input type="hidden" name="req_client_account_num" value="1101230000678"> -->
		<input type="hidden" name="req_client_num" value="HONGGILDONG1211">
		<input type="hidden" name="transfer_purpose" value="TR">
		<input type="hidden" name="req_client_bank_code" value="002">
		<input type="hidden" name="req_client_account_num" value="123123123123123">
<!-- 		<input type="hidden" name="req_client_fintech_use_num" value="120220208388941285310465"> -->
<!-- 		<input type="hidden" name="sub_frnc_name" value="하위가맹점"> -->
<!-- 		<input type="hidden" name="sub_frnc_num" value="123456789012"> -->
<!-- 		<input type="hidden" name="sub_frnc_business_num" value="1234567890"> -->
		
		<input type="hidden" name="recv_client_name" value="정해진">
		<input type="hidden" name="recv_client_bank_code" value="002">
		<input type="hidden" name="recv_client_account_num" value="300000000001">
	</form>
		<input type="submit" value="출금이체 슝 갑니다" id="clickTest">	
	
	
<form id="frm"  action="/withdraw" method="post">
  <input type = "hidden" id="jsonEle" name="jsonEle">
</form>
<div id="result">

</div>

<hr>	
<hr>	

	
	<hr>
	<hr>

<!-- <h2>입금이체 아 가봅니다 OO가자</h2> -->
	
	<form method="post" action="deposit" id="fr22">
		<input type="hidden" name="access_token" value="${responseToken.access_token }">
<!-- 		<input type="hidden" name="access_token" value="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDE0NzM5Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwib29iIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NzU4MTc5ODAsImp0aSI6IjkyNWY0YmYzLWQ3Y2MtNDNjOC05MGE4LTM5NjRmMmM3MWMzZCJ9.bt9d3Q_mKLxhtnLxQEJ3omtxYJSvVlex1GAje7Ver5Q"> -->
		<input type="hidden" name="cntr_account_type" value="N">
		<input type="hidden" name="cntr_account_num" value="200000000001">
		<input type="hidden" name="wd_pass_phrase" value="NONE">
		<input type="hidden" name="wd_print_content" value="거래금액">
		<input type="hidden" name="name_check_option" value="off">
		<input type="hidden" name="tran_dtime" value="20221104101921">
		<input type="hidden" name="req_cnt" value="1">
		
		<input type="hidden" name="tran_no" value="1">
<!-- 		<input type="hidden" name="bank_tran_id" value="M202202083U123432349"> -->
		<input type="hidden" name="fintech_use_num" value="120220208388941285547548">
		<input type="hidden" name="print_content" value="홍길동">
		<input type="hidden" name="tran_amt" value="2000">
		<input type="hidden" name="req_client_name" value="홍길동">
		<input type="hidden" name="req_client_bank_code" value="002">
		<input type="hidden" name="req_client_account_num" value="123123123123123">
<!-- 		<input type="hidden" name="req_client_fintech_use_num" value=""> -->
		<input type="hidden" name="req_client_num" value="HONGGILDONG1211">
		<input type="hidden" name="transfer_purpose" value="TR">
	</form>
		
	<form method="post" action="deposit" id="fr33">	
	</form>
	
<!-- 		<input type="button" value="입금이체다" id="depositClick"> -->




<hr>
<hr>
<hr>

<div id="result">

</div>









	
</body>
</html>


