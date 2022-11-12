package com.itwillbs.fintech.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.fintech.service.OpenBankingService;
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

@Controller
public class OpenBankingController {
	
	
	// 객체생성 
	@Autowired
	private OpenBankingService openBankingService;

	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String getToken(RequestTokenVO requestTokenVO, Model model, HttpSession session) throws Exception {
		// 인증
		System.out.println("code : " + requestTokenVO.getCode());
//		System.out.println(requestTokenVO.getScope());
//		System.out.println(requestTokenVO.getClient_info());
//		System.out.println(requestTokenVO.getState());

		// 토큰발급
		ResponseTokenVO responseToken = openBankingService.requestToken(requestTokenVO);

		session.setAttribute("token", responseToken.getAccess_token());
		System.out.println(responseToken.getAccess_token() + "q(≧▽≦q)q(≧▽≦q)q(≧▽≦q)q(≧▽≦q) ");

		// 정보들고
		model.addAttribute("responseToken", responseToken);
		return "bank_main";
	}

	// 사용자 정보 조회
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String getUserInfo(UserInfoRequestVO userInfoRequestVO, Model model) {
		// Service 객체의 findUser() 메서드를 호출하여 사용자 정보 조회
		// => 파라미터 : UserInfoRequestVO, 리턴타입 UserInfoResponseVO
		UserInfoResponseVO userInfo = openBankingService.findUser(userInfoRequestVO);

		// Model 객체에 UserInfoResponseVO 객체와 엑세스토큰 저장
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("access_token", userInfoRequestVO.getAccess_token());

		return "account/user_info";
	}

	// 등록계좌 조회
	@RequestMapping(value = "/accountList", method = RequestMethod.GET)
	public String getAccountList(AccountSearchRequestVO accountSearchRequestVO, Model model) {
		// Service 객체의 findAccount() 메서드를 호출하여 사용자 정보 조회
		// => 파라미터 : AccountSearchRequestVO, 리턴타입 AccountSearchResponseVO
		AccountSearchResponseVO accountList = openBankingService.findAccount(accountSearchRequestVO);

		// Model 객체에 AccountSearchResponseVO 객체와 엑세스토큰 저장
		model.addAttribute("accountList", accountList);
		model.addAttribute("access_token", accountSearchRequestVO.getAccess_token());

		return "account/list";
	}

//	// 계좌해지
//	@RequestMapping(value = "/accountCancel", method = RequestMethod.POST)
//	public String accountCancelPOST(AccountCancelRequestVO accountCancelRequestVO, Model model) {
//		// Service 객체의 findAccount() 메서드를 호출하여 사용자 정보 조회
//		// => 파라미터 : AccountSearchRequestVO, 리턴타입 AccountSearchResponseVO
//		AccountCancelResponseVO accountCancel = openBankingService.cancelAccount(accountCancelRequestVO);
//		
//		// Model 객체에 AccountSearchResponseVO 객체와 엑세스토큰 저장
//		//model.addAttribute("accountList", accountList);
//		//model.addAttribute("access_token", accountSearchRequestVO.getAccess_token());
//		model.addAttribute("accountCancel", accountCancel);
//		model.addAttribute("access_token", accountCancelRequestVO.getAccess_token());
//		
//		return "account/cancel";
//	}
//	
//	// 토큰만 그냥 발급
//	@RequestMapping(value = "/getTokenOne", method = RequestMethod.POST)
//	public String getTokenOne(RequestTokenVO requestTokenVO, Model model) {
//		ResponseTokenVO responseToken = openBankingService.getTokenOne(requestTokenVO);
//		System.out.println("지금 토큰완료입니다!!!!");
//		System.out.println(responseToken.getAccess_token() + "이거입니다");
//		model.addAttribute("responseToken", responseToken);
//		return "account/token";
//	}

	// 출금이체
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public @ResponseBody WithdrawResponseVO getWithdraw(@RequestBody WithdrawRequestVO withdrawRequestVO, Model model)
			throws IOException {
		// Service 객체의 findAccount() 메서드를 호출하여 사용자 정보 조회
		// => 파라미터 : AccountSearchRequestVO, 리턴타입 AccountSearchResponseVO
		// AccountSearchResponseVO accountList =
		// openBankingService.findAccount(accountSearchRequestVO);
		System.out.println(withdrawRequestVO + "@@@@@@@@@@@@@@@@@@@@@@@@");
		WithdrawResponseVO withdrawOK = openBankingService.getwithdraw(withdrawRequestVO);

		// Model 객체에 AccountSearchResponseVO 객체와 엑세스토큰 저장
		model.addAttribute("withdrawOK", withdrawOK);
		model.addAttribute("access_token", withdrawRequestVO.getAccess_token());
		System.out.println("이거찐막인데" + withdrawOK);
		// return "account/withdraw";
		return withdrawOK;
	}

	// 출금이체 결과조회
	@RequestMapping(value = "/transferResult", method = RequestMethod.POST)
	public String getTransferResult(TransferResultRequestVO transferResultRequestVO, Model model,
			TransferRequestVO transVO) {
		// Service 객체의 findAccount() 메서드를 호출하여 사용자 정보 조회
		// => 파라미터 : AccountSearchRequestVO, 리턴타입 AccountSearchResponseVO
		// AccountSearchResponseVO accountList =
		// openBankingService.findAccount(accountSearchRequestVO);
		System.out.println(transferResultRequestVO + "@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(transVO + "@@@@@@@@@@@@@@@@@@@@@@@@");
		TransferResultResponseVO transferOK = openBankingService.getTransferResult(transferResultRequestVO, transVO);

		// Model 객체에 AccountSearchResponseVO 객체와 엑세스토큰 저장
		model.addAttribute("transferOK", transferOK);
		model.addAttribute("access_token", transferResultRequestVO.getAccess_token());
		System.out.println("이거찐막인데" + transferOK);
		return "account/transferResult";
	}

//	public static String readBody(HttpServletRequest request) throws IOException {
//		BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
//		StringBuilder builder = new StringBuilder();
//		String buffer;
//		while ((buffer = input.readLine()) != null) {
//			if (builder.length() > 0) {
//				builder.append("\n");
//			}
//			builder.append(buffer);
//		}
//		return builder.toString();
//	}
//	
	
	
	//입금이체
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public @ResponseBody DepositResponseVO getDeposit(@RequestBody DepositRequestVO depositRequestVO,Model model)
			throws Exception {
		// Service 객체의 findAccount() 메서드를 호출하여 사용자 정보 조회
		// => 파라미터 : AccountSearchRequestVO, 리턴타입 AccountSearchResponseVO
		// AccountSearchResponseVO accountList =
		// openBankingService.findAccount(accountSearchRequestVO);
		System.out.println("##########################" + depositRequestVO);
		DepositResponseVO depositOK = openBankingService.getDeposit(depositRequestVO);
		System.out.println("@#@#@@#@#@#@@#갔다옴");
		//System.out.println("##########################" + depositOK);
		// Model 객체에 AccountSearchResponseVO 객체와 엑세스토큰 저장
		model.addAttribute("withdrawOK", depositOK);
		model.addAttribute("access_token", depositRequestVO.getAccess_token());
		System.out.println("이거찐막인데" + depositOK);
		// return "account/withdraw";
	
		
		return depositOK;
	}
	
	
	

	
	
	
	

}
