package com.itwillbs.fintech.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itwillbs.fintech.vo.AccountCancelRequestVO;
import com.itwillbs.fintech.vo.AccountCancelResponseVO;
import com.itwillbs.fintech.vo.AccountSearchRequestVO;
import com.itwillbs.fintech.vo.AccountSearchResponseVO;
import com.itwillbs.fintech.vo.DepositRequestVO;
import com.itwillbs.fintech.vo.DepositResponseVO;
import com.itwillbs.fintech.vo.DepositVO;
import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;
import com.itwillbs.fintech.vo.TransferRequestVO;
import com.itwillbs.fintech.vo.TransferResultRequestVO;
import com.itwillbs.fintech.vo.TransferResultResponseVO;
import com.itwillbs.fintech.vo.UserInfoRequestVO;
import com.itwillbs.fintech.vo.UserInfoResponseVO;
import com.itwillbs.fintech.vo.WithdrawRequestVO;
import com.itwillbs.fintech.vo.WithdrawResponseVO;

@Service
public class OpenBankingService {
	// OpenBankingApiClient 객체생성
	@Autowired
	private OpenBankingApiClient openBankingApiClient;

	// 토큰 발급 요청
	public ResponseTokenVO requestToken(RequestTokenVO requestTokenVO) {
		return openBankingApiClient.requestToken(requestTokenVO);
	}

	// 사용자 정보 조회
	public UserInfoResponseVO findUser(UserInfoRequestVO userInfoRequestVO) {
		return openBankingApiClient.findUser(userInfoRequestVO);
	}

	public AccountSearchResponseVO findAccount(AccountSearchRequestVO accountSearchRequestVO) {
		return openBankingApiClient.findAccount(accountSearchRequestVO);
	}

//	//계좌해지하기
//	public AccountCancelResponseVO cancelAccount(AccountCancelRequestVO accountCancelRequestVO) {
//		return openBankingApiClient.cancelAccount(accountCancelRequestVO);
//	}

//	// 토큰 발급 요청11
//		public ResponseTokenVO getTokenOne(RequestTokenVO requestTokenVO) {
//			return openBankingApiClient.getTokenOne(requestTokenVO);
//		}

	// 출금입금
	public WithdrawResponseVO getwithdraw( WithdrawRequestVO withdrawRequestVO) {
		return openBankingApiClient.withdraw(withdrawRequestVO);
	}
//	// 출금입금
//	public WithdrawResponseVO getwithdraw(String req,  WithdrawRequestVO withdrawRequestVO) throws JsonMappingException, JsonProcessingException {
//		return openBankingApiClient.withdraw(req, withdrawRequestVO);
//	}

	// 출금입금 결과조회
	public TransferResultResponseVO getTransferResult(TransferResultRequestVO transferResultRequestVO, TransferRequestVO transVO) {
		return openBankingApiClient.transferResult(transferResultRequestVO, transVO);
	}
	
	// 입금이체
	public DepositResponseVO getDeposit( DepositRequestVO depositRequestVO) throws Exception {
		
		return openBankingApiClient.deposit(depositRequestVO);
	}

}
