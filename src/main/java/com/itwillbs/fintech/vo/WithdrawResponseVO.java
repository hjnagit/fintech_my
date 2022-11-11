package com.itwillbs.fintech.vo;

import lombok.Data;

@Data
public class WithdrawResponseVO {
	//출금이체 - 응답
	private String api_tran_id; //거래고유번호
	private String api_tran_dtm; //거래일시-밀리세컨드
	private String rsp_code; //응답코드
	private String rsp_message; //응답메세지
	private String dps_bank_code_std; //입금기관 표준코드
	private String dps_bank_code_sub; // 입금기관 점별코드
	private String dps_bank_name; //입금기관명
	private String dps_account_num_masked; //입금계좌번호(출력용)
	private String dps_print_content; //입금계좌인자내역
	private String dps_account_holder_name; //수취인성명
	private String bank_tran_id; //거래고유번호(참가은행)
	
	
	private String bank_tran_date; //거래일자(참가은행)
	private String bank_code_tran; //
	private String bank_rsp_code; //
	private String bank_rsp_message; 
	private String fintech_use_num; //출금계좌 핀테크 이용번호
	private String account_alias;
	private String bank_code_std; //출금기관 표준코드
	private String bank_code_sub; //출금기관 점별코드
	private String bank_name; //출금기관명
	private String savings_bank_name; //개별저축은행명
	private String account_num_masked; //출금계좌번호(출력용)
	private String print_content; //출금계좌인자내역
	private String account_holder_name; //송금인 성명
	private String tran_amt; //거래금액
	private String wd_limit_remain_amt; //출금한도잔여금액
	
	
	
	
}
