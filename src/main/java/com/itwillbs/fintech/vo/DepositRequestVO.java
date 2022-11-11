package com.itwillbs.fintech.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class DepositRequestVO {
	//입금이체 - 요청  deposit/fin_num
	
	private String access_token;
	private String cntr_account_type; //약정 계좌N/계정Y
	private String cntr_account_num; //약정 계좌/ㄱㅖ정본호
	private String wd_pass_phrase; //입금이체용 암호문구
	private String wd_print_content; //출금계좌인자내역
	private String name_check_option; //수취인성명 검정여부 on
	private String tran_dtime; //요청일시 14자리
	private String req_cnt; //입금요청건수 1
	private List req_list; //입금요청목록
	
	private String tran_no; //거래순ㄹ번 1
	private String bank_tran_id; //은행거래고유번호
	private String fintech_use_num; //핀테크이용번호
	private String print_content; //입금계좌인자내역
	private String tran_amt; //거래금액
	private String req_client_name; //요청고객성명
	private String req_client_bank_code;
	private String req_client_account_num;
	
	
	private String req_client_num; //요청고객회원번호
	private String transfer_purpose; //이체용도 TR
	
	public String getBank_tran_id() {
		return "M202202083U"+ (int)((Math.random()+1)*100000000);
	}
//	public void setBank_tran_id() {
//		this.bank_tran_id = "M202202083U"+ (Math.random()+1)*100000000;
//	}


	
}
