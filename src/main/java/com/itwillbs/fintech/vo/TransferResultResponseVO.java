package com.itwillbs.fintech.vo;

import java.util.List;

import lombok.Data;

@Data
public class TransferResultResponseVO {
	//이체결과조회 응답
	
	private String api_tran_id;
	private String api_tran_dtm;
	private String rsp_code;
	private String rsp_message;
	private String res_cnt;
	private List<TransferResponseVO> res_list;
	

	
	
	
}
