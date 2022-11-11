package com.itwillbs.fintech.vo;

import lombok.Data;

@Data
public class AccountCancelResponseVO {
	//계좌해지 - 응답
	
	private String api_tran_id; //거래고유번호
	private String api_tran_dtm; //거래일시
	private String rsp_code; //응답코드
	private String rsp_message; //응답메세지
	private String bank_tran_id; //거래고유번호
	private String bank_tran_date; //거래일자
	private String bank_code_tran; //참가은행 표준코드
	private String bank_rsp_code; //응답코드
	private String bank_rsp_message; //응답메시지
	
}
