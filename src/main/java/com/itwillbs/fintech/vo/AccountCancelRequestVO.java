package com.itwillbs.fintech.vo;

import lombok.Data;

@Data
public class AccountCancelRequestVO {
	//계좌 삭제 - 요청
	
	private String access_token; 
	private String bank_tran_id; //은행고유번호
	private String scope; //서비스  [inquiry|transfer]
	private String fintech_use_num; //핀테크이용번호
	
	
}
