package com.itwillbs.fintech.vo;

import lombok.Data;

@Data
public class TransferResponseVO {
	private String tran_no;
	private String bank_tran_id;
	private String bank_tran_date;
	private String bank_code_tran;
	private String bank_rsp_code;
	private String bank_rsp_message;
	private String wd_bank_code_std;
	private String wd_bank_code_sub;
	private String wd_bank_name;
	private String wd_savings_bank_name;
	private String wd_fintech_use_num;
	private String wd_account_num_masked;
	private String wd_print_content;
	private String wd_account_holder_name;
	private String dps_bank_code_std;
	private String dps_bank_code_sub;
	private String dps_bank_name;
	private String dps_savings_bank_name;
	private String dps_fintech_use_num;
	private String dps_account_num_masked;
	private String dps_print_content;
	private String dps_account_holder_name;
	private String tran_amt;
}
