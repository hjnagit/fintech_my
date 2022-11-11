package com.itwillbs.fintech.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class OpenBankingApiClient {
	// 변수 정의 
	private String client_id="6f948a13-11af-4892-b746-ee67d358abf2";
	private String client_secret="4008271b-939d-4ee9-a981-5a132490eafc";
	private String redirect_uri="http://localhost:8088/fintech/callback";
	private String grant_type="authorization_code";
	// 기본 주소
	private String baseUrl = "https://testapi.openbanking.or.kr/v2.0";
	
	// REST 방식 API 요청
	private RestTemplate restTemplate;
	// 헤더 정보 관리 클래스 
	private HttpHeaders httpHeaders;
	
	// 헤더에 엑세스 토큰을 추가하는 setHeaderAccessToken() 메서드 정의
	// => 파라미터 : 엑세스토큰, 리턴타입 : HttpHeaders
	public HttpHeaders setHeaderAccessToken(String access_token) {
		// HttpHeaders 객체의 add() 메서드를 호출하여 "항목", "값" 형태로 파라미터 전달
		httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
		//httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		//httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		httpHeaders.add("Authorization", "Bearer " + access_token);
		return httpHeaders;
	}
	
	
	public ResponseTokenVO requestToken(RequestTokenVO requestTokenVO) {
//		요청 메시지 URL
//HTTP URL 	https://testapi.openbanking.or.kr/oauth/2.0/token
//HTTP Method POST
//Content-Type application/x-www-form-urlencoded; charset=UTF-8
//요청값code client_id client_secret redirect_uri grant_type
		restTemplate=new RestTemplate();
		httpHeaders=new HttpHeaders();
		// Content-Type 지정 http header
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
		// application/x-www-form-urlencoded; charset=UTF-8" 객체저장 불가능
		MultiValueMap<String, String> parameters
		=new LinkedMultiValueMap<String, String>();
		parameters.add("code", requestTokenVO.getCode());
		parameters.add("client_id", requestTokenVO.getClient_id());
		parameters.add("client_secret", requestTokenVO.getClient_secret());
		parameters.add("redirect_uri", requestTokenVO.getRedirect_uri());
		parameters.add("grant_type", requestTokenVO.getGrant_type());
		
		// HttpHeader,HttpBody  parameters 담아서 감 => HttpEntity
		HttpEntity<MultiValueMap<String, String>> param=
new HttpEntity<MultiValueMap<String,String>>(parameters,httpHeaders);
		
		String requestUrl="https://testapi.openbanking.or.kr/oauth/2.0/token";
		return restTemplate.exchange(requestUrl, 
				HttpMethod.POST, param, ResponseTokenVO.class).getBody();
	}
	
	// 사용자 정보 조회
		public UserInfoResponseVO findUser(UserInfoRequestVO userInfoRequestVO) {
			/// REST 방식 요청에 필요한 객체 생성
			restTemplate = new RestTemplate();
			httpHeaders = new HttpHeaders();
			
			// 2.2.1 사용자정보조회 API URL 주소 생성
			String url = baseUrl + "/user/me";
			
			// HttpHeaders 와 HttpBody 오브젝트를 하나의 객체로 관리하기 위한 HttpEntity 객체 생성
			// => 파라미터로 HttpHeaders 객체 전달을 위해 
			//    헤더 생성 작업을 수행하는 사용자 정의 메서드 setHeaderAccessToken() 호출
			//    (파라미터로 엑세스 토큰 전달 => UserInfoRequestVO 객체에 저장되어 있음)
			HttpEntity<String> openBankingUserInfoRequest = 
					new HttpEntity<String>(setHeaderAccessToken(userInfoRequestVO.getAccess_token()));
			
			// UriComponentsBuilder 클래스의 fromHttpUrl() 메서드를 호출하여 URL 파라미터 정보 생성
			// 1단계. UriComponentsBuilder.fromHttpUrl() 메서드를 호출하여 요청 URL 주소 전달
			// 2단계. 1단계에서 생성된 객체의 queryParam() 메서드를 호출하여 전달할 파라미터를
			//        키, 값 형식으로 전달
			// 3단계. 2단계에서 생성된 객체의 build() 메서드를 호출하여 UriComponents 객체 리턴(생성)
			// 위의 세 과정을 빌더 패턴(Builder Pattern)을 활용하여 하나의 문장으로 압축 가능
			// (자기 자신을 리턴하는 메서드 호출 후 연쇄적으로 메서드를 이어나가는 것)
			UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("user_seq_no", userInfoRequestVO.getUser_seq_no())
					.build();
			
			// exchange() 메서드 파라미터 : UriBuilder 문자열로 변환, 요청방식, HttpEntity 객체,
			//                              응답데이터를 파싱하기 위한 클래스(.class 필수)
			// => 메서드 뒤에 .getBody() 메서드를 호출하여 body 데이터에 대한 파싱된 결과를 리턴받기
			return restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, openBankingUserInfoRequest, UserInfoResponseVO.class).getBody();
		}
		
		//계좌리스트
		public AccountSearchResponseVO findAccount(AccountSearchRequestVO accountSearchRequestVO) {
			/// REST 방식 요청에 필요한 객체 생성
			restTemplate = new RestTemplate();
			httpHeaders = new HttpHeaders();
			
			// 2.2.1 사용자정보조회 API URL 주소 생성
			String url = baseUrl + "/account/list";

			httpHeaders.add("Authorization", "Bearer " + accountSearchRequestVO.getAccess_token());
			
			HttpEntity<String> openBankingAccountListRequest = new HttpEntity<String>(httpHeaders);
			
			UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("user_seq_no", accountSearchRequestVO.getUser_seq_no())
					.queryParam("include_cancel_yn", accountSearchRequestVO.getInclude_cancel_yn())
					.queryParam("sort_order", accountSearchRequestVO.getSort_order())
					.build();
			
			return restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, openBankingAccountListRequest, AccountSearchResponseVO.class).getBody();
		}
		
//		//계좌해지하기
//		public AccountCancelResponseVO cancelAccount(AccountCancelRequestVO accountCancelRequestVO) {
//			/// REST 방식 요청에 필요한 객체 생성
//			restTemplate = new RestTemplate();
//			httpHeaders = new HttpHeaders();
//			
//			// 2.2.1 계좌해지 API URL 주소 생성
//			String url = baseUrl + "/account/cancel";
//			
//			httpHeaders.add("Authorization", "Bearer " + accountCancelRequestVO.getAccess_token());
//			
//			HttpEntity<String> openBankingAccountCancelRequest = new HttpEntity<String>(httpHeaders);
//			
//			UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
//					.queryParam("bank_tran_id", accountCancelRequestVO.getBank_tran_id())
//					.queryParam("scope", accountCancelRequestVO.getScope())
//					.queryParam("fintech_use_num", accountCancelRequestVO.getFintech_use_num())
//					.build();
//			
//			return restTemplate.exchange(uriBuilder.toString(), HttpMethod.POST, openBankingAccountCancelRequest, AccountCancelResponseVO.class).getBody();
//		}
		
		
		//토큰그거
//		public ResponseTokenVO getTokenOne(RequestTokenVO requestTokenVO) {
//			// REST 방식 요청에 필요한 객체 생성
//			restTemplate = new RestTemplate();
//			httpHeaders = new HttpHeaders();
//			
//			// 2.2.1 계좌해지 API URL 주소 생성
//			String url = baseUrl + "/token";
//			
//			httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//			HttpEntity<String> openBankingTokenRequest = new HttpEntity<String>(httpHeaders);
//			
//			UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
//					.queryParam("code", requestTokenVO.getCode())
//					.queryParam("client_id", requestTokenVO.getClient_id())
//					.queryParam("client_secret", requestTokenVO.getClient_secret())
//					.queryParam("redirect_uri", requestTokenVO.getRedirect_uri())
//					.queryParam("grant_type", requestTokenVO.getRedirect_uri())
//					.build();
//					
//			
//			return restTemplate.exchange(uriBuilder.toString(), 
//					HttpMethod.POST,openBankingTokenRequest, ResponseTokenVO.class).getBody();
//			
//			restTemplate=new RestTemplate();
//			httpHeaders=new HttpHeaders();
//			// Content-Type 지정 http header
//			httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//			
//			requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
//			// application/x-www-form-urlencoded; charset=UTF-8" 객체저장 불가능
//			MultiValueMap<String, String> parameters
//			=new LinkedMultiValueMap<String, String>();
//			parameters.add("code", requestTokenVO.getCode());
//			parameters.add("client_id", requestTokenVO.getClient_id());
//			parameters.add("client_secret", requestTokenVO.getClient_secret());
//			parameters.add("redirect_uri", requestTokenVO.getRedirect_uri());
//			parameters.add("grant_type", requestTokenVO.getGrant_type());
//			
//			// HttpHeader,HttpBody  parameters 담아서 감 => HttpEntity
//			HttpEntity<MultiValueMap<String, String>> param=
//	new HttpEntity<MultiValueMap<String,String>>(parameters,httpHeaders);
//			
//			String requestUrl="https://testapi.openbanking.or.kr/oauth/2.0/token";
//			return restTemplate.exchange(requestUrl, 
//					HttpMethod.POST, param, ResponseTokenVO.class).getBody();
//			
//		}
		
//		// 출금이체
//		public WithdrawResponseVO withdraw( String req, WithdrawRequestVO withdrawRequestVO) throws JsonMappingException, JsonProcessingException {
//			/// REST 방식 요청에 필요한 객체 생성
//			restTemplate = new RestTemplate();
//			httpHeaders = new HttpHeaders();
//			
//			//httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//			//httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
//			//httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
//			 ObjectMapper mapper = new ObjectMapper();
//			//httpHeaders.add("Authorization", "Bearer " + withdrawRequestVO.getAccess_token());
//			//setHeaderAccessToken(withdrawRequestVO.getAccess_token()
//			//requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
//			// application/x-www-form-urlencoded; charset=UTF-8" 객체저장 불가능
//			Map<String, String> parameters = mapper.readValue(req, Map.class);
////			parameters.add("bank_tran_id", withdrawRequestVO.getBank_tran_id());
////			parameters.add("cntr_account_type", withdrawRequestVO.getCntr_account_type());
////			parameters.add("cntr_account_num", withdrawRequestVO.getCntr_account_num());
////			parameters.add("dps_print_content", withdrawRequestVO.getDps_print_content());
////			parameters.add("fintech_use_num", withdrawRequestVO.getFintech_use_num());
////			parameters.add("tran_amt", withdrawRequestVO.getTran_amt());
////			parameters.add("tran_dtime", withdrawRequestVO.getTran_dtime());
////			parameters.add("req_client_name", withdrawRequestVO.getReq_client_name());
////			parameters.add("req_client_num", withdrawRequestVO.getReq_client_num());
////			parameters.add("transfer_purpose", withdrawRequestVO.getTransfer_purpose());
//			
//			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+req);
//			
//			// HttpHeader,HttpBody parameters 담아서 감 => HttpEntity
//			//HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<MultiValueMap<String, String>>(parameters, httpHeaders);
//			HttpEntity<Map<String, String>> param = new HttpEntity<Map<String, String>>(parameters, setHeaderAccessToken(withdrawRequestVO.getAccess_token()));
//
//			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@토큰넣은");
//			//String requestUrl = "https://testapi.openbanking.or.kr/oauth/2.0/transfer/withdraw/fin_num";
//			String requestUrl = "https://testapi.openbanking.or.kr/v2.0/transfer/withdraw/fin_num";
//			
//			
//			
//			//return restTemplate.exchange(requestUrl, HttpMethod.POST, param, WithdrawResponseVO.class).getBody();
//			return restTemplate.postForEntity(requestUrl, param, WithdrawResponseVO.class).getBody();
//		}	
		
//		// 출금이체 -  찐
//		public WithdrawResponseVO withdraw(@RequestBody WithdrawRequestVO withdrawRequestVO) {
//			/// REST 방식 요청에 필요한 객체 생성
//			restTemplate = new RestTemplate();
//			httpHeaders = new HttpHeaders();
//			
//			//httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//			//httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
//			//httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
//			
//			//httpHeaders.add("Authorization", "Bearer " + withdrawRequestVO.getAccess_token());
//			//setHeaderAccessToken(withdrawRequestVO.getAccess_token()
//			//requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
//			// application/x-www-form-urlencoded; charset=UTF-8" 객체저장 불가능
//			//MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
//			Map<String, String> parameters = new HashMap<String, String>();
//			parameters.put("bank_tran_id", "M202202083U7BC366361");
//			parameters.put("cntr_account_type", "N");
//			parameters.put("cntr_account_num", "123213213123");
//			parameters.put("dps_print_content", "kkkk");
//			parameters.put("fintech_use_num", "120220208388941285310465");
//			parameters.put("tran_amt", "1000");
//			parameters.put("tran_dtime", "20221104101921");
//			parameters.put("req_client_name", "홍길동");
//			parameters.put("req_client_num", "HONGGILDONG1238");
//			parameters.put("transfer_purpose", "TR");
//			
//			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+parameters);
//			
//			// HttpHeader,HttpBody parameters 담아서 감 => HttpEntity
//			//HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<MultiValueMap<String, String>>(parameters, httpHeaders);
//			HttpEntity<Map<String, String>> param = new HttpEntity<Map<String, String>>(parameters, setHeaderAccessToken(withdrawRequestVO.getAccess_token()));
//			
//			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@토큰넣은");
//			//String requestUrl = "https://testapi.openbanking.or.kr/oauth/2.0/transfer/withdraw/fin_num";
//			String requestUrl = "https://testapi.openbanking.or.kr/v2.0/transfer/withdraw/fin_num";
//			
//			
//			
//			//return restTemplate.exchange(requestUrl, HttpMethod.POST, param, WithdrawResponseVO.class).getBody();
//			return restTemplate.postForEntity(requestUrl, param, WithdrawResponseVO.class).getBody();
//		}	
		
		// 출금이체
		public WithdrawResponseVO withdraw( WithdrawRequestVO withdrawRequestVO) {
			/// REST 방식 요청에 필요한 객체 생성
			restTemplate = new RestTemplate();
			httpHeaders = new HttpHeaders();
			
			//httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			//httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
			//httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
			
			//httpHeaders.add("Authorization", "Bearer " + withdrawRequestVO.getAccess_token());
			//setHeaderAccessToken(withdrawRequestVO.getAccess_token()
			//requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
			// application/x-www-form-urlencoded; charset=UTF-8" 객체저장 불가능
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("bank_tran_id", withdrawRequestVO.getBank_tran_id());
			parameters.put("cntr_account_type", withdrawRequestVO.getCntr_account_type());
			parameters.put("cntr_account_num", withdrawRequestVO.getCntr_account_num());
			parameters.put("dps_print_content", withdrawRequestVO.getDps_print_content());
			parameters.put("fintech_use_num", withdrawRequestVO.getFintech_use_num());
			parameters.put("tran_amt", withdrawRequestVO.getTran_amt());
			parameters.put("tran_dtime", withdrawRequestVO.getTran_dtime());
			parameters.put("req_client_name", withdrawRequestVO.getReq_client_name());
			parameters.put("req_client_num", withdrawRequestVO.getReq_client_num());
			parameters.put("transfer_purpose", withdrawRequestVO.getTransfer_purpose());
			parameters.put("req_client_bank_code", withdrawRequestVO.getReq_client_bank_code());
			parameters.put("req_client_account_num", withdrawRequestVO.getReq_client_account_num());
			//parameters.put("req_client_fintech_use_num", withdrawRequestVO.getReq_client_fintech_use_num());
			parameters.put("recv_client_name", withdrawRequestVO.getRecv_client_name());
			parameters.put("recv_client_bank_code", withdrawRequestVO.getRecv_client_bank_code());
			parameters.put("recv_client_account_num", withdrawRequestVO.getRecv_client_account_num());
			
			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+parameters);
			
			// HttpHeader,HttpBody parameters 담아서 감 => HttpEntity
			//HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<MultiValueMap<String, String>>(parameters, httpHeaders);
			HttpEntity<Map<String, String>> param = new HttpEntity<Map<String, String>>(parameters, setHeaderAccessToken(withdrawRequestVO.getAccess_token()));
			
			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@토큰넣은" +param);
			//String requestUrl = "https://testapi.openbanking.or.kr/oauth/2.0/transfer/withdraw/fin_num";
			String requestUrl = "https://testapi.openbanking.or.kr/v2.0/transfer/withdraw/fin_num";
			
			
			
			//return restTemplate.exchange(requestUrl, HttpMethod.POST, param, WithdrawResponseVO.class).getBody();
			return restTemplate.postForEntity(requestUrl, param, WithdrawResponseVO.class).getBody();
		}	
		
		
//		// 출금이체
//		public WithdrawResponseVO withdraw(WithdrawRequestVO withdrawRequestVO) {
//			/// REST 방식 요청에 필요한 객체 생성
//			restTemplate = new RestTemplate();
//			httpHeaders = new HttpHeaders();
	//
//			// 2.2.1 사용자정보조회 API URL 주소 생성
//			String url = baseUrl + "/transfer/withdraw/fin_num";
//			//httpHeaders.add("Authorization", "Bearer " + accountSearchRequestVO.getAccess_token());
	//
//			httpHeaders.add("Authorization", "Bearer " + withdrawRequestVO.getAccess_token());
//			HttpEntity<String> openBankingWithdrawRequest = new HttpEntity<String>(httpHeaders);
//			
//			//HttpEntity<String> openBankingAccountListRequest = new HttpEntity<String>(setHeaderAccessToken(withdrawRequestVO.getAccess_token()));
//			UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
//					.queryParam("bank_tran_id", withdrawRequestVO.getBank_tran_id())
//					.queryParam("cntr_account_type", withdrawRequestVO.getCntr_account_type())
//					.queryParam("cntr_account_num", withdrawRequestVO.getCntr_account_num())
//					.queryParam("dps_print_content", withdrawRequestVO.getDps_print_content())
//					.queryParam("fintech_use_num", withdrawRequestVO.getFintech_use_num())
//					.queryParam("tran_amt", withdrawRequestVO.getTran_amt())
//					.queryParam("tran_dtime", withdrawRequestVO.getTran_dtime())
//					.queryParam("req_client_name", withdrawRequestVO.getReq_client_name())
//					.queryParam("req_client_num", withdrawRequestVO.getReq_client_num())
//					.queryParam("transfer_purpose", withdrawRequestVO.getTransfer_purpose())
//					//.queryParam("recv_client_name", withdrawRequestVO.getRecv_client_name())
//					//.queryParam("recv_client_bank_code", withdrawRequestVO.getRecv_client_bank_code())
//					//.queryParam("recv_client_account_num", withdrawRequestVO.getRecv_client_account_num())
//					.build();
//			System.out.println(uriBuilder+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");		
	//
//			return restTemplate.exchange(uriBuilder.toString(), HttpMethod.POST, openBankingWithdrawRequest,
//					WithdrawResponseVO.class).getBody();
//			//return restTemplate
//			//		.exchange(uriBuilder.toString(), HttpMethod.GET, openBankingUserInfoRequest, UserInfoResponseVO.class)
//			//		.getBody();
//		}	
		
		
		//이체결과조회
		public TransferResultResponseVO transferResult(@RequestBody TransferResultRequestVO transferResultRequestVO, TransferRequestVO transVO) {
			/// REST 방식 요청에 필요한 객체 생성
			restTemplate = new RestTemplate();
			httpHeaders = new HttpHeaders();
			
			//httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			//httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
			//httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
			
			List<TransferRequestVO> req_list = new ArrayList<TransferRequestVO>();
			req_list.add(transVO);
			
			
			//httpHeaders.add("Authorization", "Bearer " + withdrawRequestVO.getAccess_token());
			//setHeaderAccessToken(withdrawRequestVO.getAccess_token()
			//requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
			// application/x-www-form-urlencoded; charset=UTF-8" 객체저장 불가능
			MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
			parameters.add("check_type", transferResultRequestVO.getCheck_type());
			parameters.add("tran_dtime", transferResultRequestVO.getTran_dtime());
			parameters.add("req_cnt", transferResultRequestVO.getReq_cnt());
			//parameters.addAll("req_list", transferResultRequestVO.getRes_list());
			parameters.addAll("req_list", req_list);
			
			
			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+parameters);
			
			// HttpHeader,HttpBody parameters 담아서 감 => HttpEntity
			//HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<MultiValueMap<String, String>>(parameters, httpHeaders);
			HttpEntity<MultiValueMap<String, Object>> param = new HttpEntity<MultiValueMap<String, Object>>(parameters, setHeaderAccessToken(transferResultRequestVO.getAccess_token()));

			System.out.println("저정완@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@토큰넣은" + param);
			//String requestUrl = "https://testapi.openbanking.or.kr/oauth/2.0/transfer/withdraw/fin_num";
			String requestUrl = "https://testapi.openbanking.or.kr/v2.0/transfer/result";
			
			
			
			//return restTemplate.postForEntity(requestUrl, param, WithdrawResponseVO.class).getBody();
			return restTemplate.exchange(requestUrl, HttpMethod.POST, param, TransferResultResponseVO.class).getBody();
		}	
		
		
		// 입금이체
		public DepositResponseVO deposit( DepositRequestVO depositRequestVO) throws Exception{
			/// REST 방식 요청에 필요한 객체 생성
			restTemplate = new RestTemplate();
			httpHeaders = new HttpHeaders();
			
			//httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			//httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
			//httpHeaders.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
			
//			List<DepositVO> req_list = new ArrayList<DepositVO>();
//			req_list.add(depositVO);
//			
			//httpHeaders.add("Authorization", "Bearer " + withdrawRequestVO.getAccess_token());
			//setHeaderAccessToken(withdrawRequestVO.getAccess_token()
			//requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
			// application/x-www-form-urlencoded; charset=UTF-8" 객체저장 불가능
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("cntr_account_type", depositRequestVO.getCntr_account_type());
			parameters.put("cntr_account_num", depositRequestVO.getCntr_account_num());
			parameters.put("wd_pass_phrase", depositRequestVO.getWd_pass_phrase());
			parameters.put("wd_print_content", depositRequestVO.getWd_print_content());
			parameters.put("name_check_option", depositRequestVO.getName_check_option());
			parameters.put("tran_dtime", depositRequestVO.getTran_dtime());
			parameters.put("req_cnt", depositRequestVO.getReq_cnt());
			
			
			Map<String, String> parameters2 = new HashMap<String, String>();
			parameters2.put("tran_no", depositRequestVO.getTran_no());
			parameters2.put("bank_tran_id", depositRequestVO.getBank_tran_id());
//			parameters2.put("bank_tran_id", depositRequestVO.getBank_tran_id());
//			parameters2.put("bank_tran_id", "M202202083U123432345");
			parameters2.put("fintech_use_num", depositRequestVO.getFintech_use_num());
			parameters2.put("print_content", depositRequestVO.getPrint_content());
			parameters2.put("tran_amt", depositRequestVO.getTran_amt());
			parameters2.put("req_client_name", depositRequestVO.getReq_client_name());
			parameters2.put("req_client_bank_code", depositRequestVO.getReq_client_bank_code());
			parameters2.put("req_client_account_num", depositRequestVO.getReq_client_account_num());
			parameters2.put("req_client_num", depositRequestVO.getReq_client_num());
			parameters2.put("transfer_purpose", depositRequestVO.getTransfer_purpose());
			
			List<Map> req_list = new ArrayList<Map>();
			req_list.add(parameters2);
			//depositRequestVO.setReq_list(req_list);
			parameters.put("req_list", req_list);
			System.out.println("저정완###############################"+parameters);
			
			// HttpHeader,HttpBody parameters 담아서 감 => HttpEntity
			//HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<MultiValueMap<String, String>>(parameters, httpHeaders);
			HttpEntity<Map<String, Object>> param = new HttpEntity<Map<String, Object>>(parameters, setHeaderAccessToken(depositRequestVO.getAccess_token()));
			
			System.out.println("저정완###############################토큰넣은");
			String requestUrl = "https://testapi.openbanking.or.kr/v2.0/transfer/deposit/fin_num";
			
			
			
			System.out.println("저정완###############################주소저장" + param);
			return restTemplate.exchange(requestUrl, HttpMethod.POST, param, DepositResponseVO.class).getBody();
			//return restTemplate.postForEntity(requestUrl, param, DepositResponseVO.class).getBody();
		}	
		
}
