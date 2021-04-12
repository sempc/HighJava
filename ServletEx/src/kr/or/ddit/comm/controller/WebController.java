package kr.or.ddit.comm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  사용자 요청을 받아서 처리하는 컨트롤러 구현하기
 * @author SEM-pc
 *
 */
public class WebController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	
	/**
	 * 요청 처리 메서드
	 * @param req
	 * @param resp
	 */
	private void process(HttpServletRequest req, 
				HttpServletResponse resp) {
		
		String reqURI = req.getRequestURI();
		
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			// ContenxtPath 부분을 제외한 URL 정보 가져오기
			reqURI = reqURI.substring(
					req.getContextPath().length());
		}
		
		if(reqURI.equals("/member/list.do")) { // 회원목록 조회
			// 회원목록 조회기능 호출...
		}else if(reqURI.equals("/member/insert.do")) {// 회원등록
			if(req.getMethod().equals("GET")) {
				// 등록을 위한 폼페이지 이동
			}else if(req.getMethod().equals("POST")) {
				// 회원등록 처리
			}
		}
		//.. 등등...
		
	/*
	    코맨드(Command)패턴이란 ?
	    
	  사용자 요청에 대한 실제 처리 기능을 커맨드 객체로 캡슐화하여 처리하는 패턴
	  
	 Command : 사용자 요청을 캡슐화한 객체(실제 처리기능을 구현한 객체)
	 Invoker : 사용자 요청에 대응되는 적당한 커맨드 객체를 찾아 실행 해주는 역할을 
	            하는 객체
	            
	- 장점 : 요청을 처리하는 객체(Invoker)로부터 실제 수행 기능을 분리함으로써,
	       새로운 기능을 추가하는데 보다 수월하다.
  => 새로운 기능(Command)을 추가할 때 기존 기능을 수정할 필요가 없다.(유지보수 수월함)
	    	 
	*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
