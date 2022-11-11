package com.itwillbs.fintech.vo;

import java.util.List;

import lombok.Data;

@Data
public class TransferResultRequestVO {
	//이체결과조회 - 요청
	
	private String access_token;
	private String check_type; //출금이체 1
	private String tran_dtime; //요청일시
	private String req_cnt; //요청건수 - 한번에 25건 확인가능
	private List<TransferRequestVO> res_list; //요청목록\
	
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public String getTran_dtime() {
		return tran_dtime;
	}
	public void setTran_dtime(String tran_dtime) {
		this.tran_dtime = tran_dtime;
	}
	public String getReq_cnt() {
		return req_cnt;
	}
	public void setReq_cnt(String req_cnt) {
		this.req_cnt = req_cnt;
	}
	public List<TransferRequestVO> getRes_list() {
		return res_list;
	}
	public void setRes_list(List<TransferRequestVO> res_list) {
		this.res_list = res_list;
	}

	
	
}
