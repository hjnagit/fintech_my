package com.itwillbs.fintech.vo;

import lombok.Data;

@Data
public class TransferRequestVO {
	private String tran_no; //거래순번
	private String org_bank_tran_id; //원거래 거래고유번호
	private String org_bank_tran_date; //원거래 거래일자
	private String org_tran_amt; //원거래금액
}
