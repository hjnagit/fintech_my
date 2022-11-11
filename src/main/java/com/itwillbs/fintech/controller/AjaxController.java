package com.itwillbs.fintech.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.fintech.service.OpenBankingService;
import com.itwillbs.fintech.vo.AccountCancelRequestVO;
import com.itwillbs.fintech.vo.AccountCancelResponseVO;
import com.itwillbs.fintech.vo.AccountSearchRequestVO;
import com.itwillbs.fintech.vo.AccountSearchResponseVO;
import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;
import com.itwillbs.fintech.vo.UserInfoRequestVO;
import com.itwillbs.fintech.vo.UserInfoResponseVO;
import com.itwillbs.fintech.vo.WithdrawRequestVO;
import com.itwillbs.fintech.vo.WithdrawResponseVO;

@RestController
public class AjaxController {
	// 객체생성
	@Autowired
	private OpenBankingService openBankingService;

	
	//출금이체
//	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
//	public @ResponseBody WithdrawResponseVO getWithdraw(@RequestBody WithdrawRequestVO withdrawRequestVO, Model model) {
//		// Service 객체의 findAccount() 메서드를 호출하여 사용자 정보 조회
//		// => 파라미터 : AccountSearchRequestVO, 리턴타입 AccountSearchResponseVO
//		//AccountSearchResponseVO accountList = openBankingService.findAccount(accountSearchRequestVO);
//		System.out.println(withdrawRequestVO+"@@@@@@@@@@@@@@@@@@@@@@@@");
//		WithdrawResponseVO withdrawOK = openBankingService.getwithdraw(withdrawRequestVO);
//
//		// Model 객체에 AccountSearchResponseVO 객체와 엑세스토큰 저장
//		//model.addAttribute("withdrawOK", withdrawOK);
//		//model.addAttribute("access_token", withdrawRequestVO.getAccess_token());
//		System.out.println("이거찐막인데"+withdrawOK);
////		return "account/withdraw";
//		return withdrawOK;
//	}
	
	
//	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
//	public  WithdrawResponseVO getWithdraw(@RequestBody WithdrawRequestVO withdrawRequestVO, Model model) {
//		// Service 객체의 findAccount() 메서드를 호출하여 사용자 정보 조회
//		// => 파라미터 : AccountSearchRequestVO, 리턴타입 AccountSearchResponseVO
//		//AccountSearchResponseVO accountList = openBankingService.findAccount(accountSearchRequestVO);
//		System.out.println(withdrawRequestVO+"@@@@@@@@@@@@@@@@@@@@@@@@");
//		WithdrawResponseVO withdrawOK = openBankingService.getwithdraw(withdrawRequestVO);
//		
//		// Model 객체에 AccountSearchResponseVO 객체와 엑세스토큰 저장
//		//model.addAttribute("withdrawOK", withdrawOK);
//		//model.addAttribute("access_token", withdrawRequestVO.getAccess_token());
//		System.out.println("이거찐막인데"+withdrawOK);
////		return "account/withdraw";
//		return withdrawOK;
//	}

}
