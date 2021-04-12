package kr.or.ddit.comm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.comm.handler.CommandHandler;
import kr.or.ddit.comm.handler.NullHandler;

/**
 *  사용자 요청을 받아서 처리하는 컨트롤러 구현하기
 * @author SEM-pc
 *
 */
public class WebController extends HttpServlet {
	
	private static Logger LOGGER = 
			Logger.getLogger(WebController.class); 
	
	// 매핑정보 저장 (핸들러 객체 저장용 맵)
	private Map<String, CommandHandler> cmmHandlerMap
		= new HashMap<>(); 
	
	
	
	
	
	
	
	
	
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
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void process(HttpServletRequest req, 
				HttpServletResponse resp) throws IOException, ServletException {
		
		String reqURI = req.getRequestURI();
		
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			// ContenxtPath 부분을 제외한 URL 정보 가져오기
			reqURI = reqURI.substring(
					req.getContextPath().length());
		}
		
		/*
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
		*/
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info("command : " + reqURI); 
			LOGGER.info("cmmHandlerMap : " + cmmHandlerMap); 
		}
		
		CommandHandler handler = 
				cmmHandlerMap.get(reqURI);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = "";
		try {
			// 핸들러 처리부분
			viewPage = handler.process(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info("viewPage : " + viewPage);
		
		// VIEW 화면 처리
		if(viewPage != null) { // 뷰페이지가 존재하는 경우...
			// 리다이렉트 처리가 필요한 경우
			if(handler.isRedirect(req)) { 
				resp.sendRedirect(viewPage);
			}else {
				RequestDispatcher dispatcher 
				= req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
		
		
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
