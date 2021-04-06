package kr.or.ddit.basic;

/**
 	세션(HttpSession) 객체에 대하여...
 	
 	- 세션을 통해서 사용자(웹브라우저)별로 구분하여 정보를 관리할 수 있다.
 	  (세션ID 이용)
 	 쿠키를 사용할 때보다 보안이 향상된다.(정보가 서버에 저장되기 때문에...)
 	 
 	- 세션객체를 가져오는 방법
 	HttpSession session = request.getSession(boolean값);
 	boolean값 : true인 경우 => 세션객체가 존재하지 않으면 새로 생성함.
 	           false인 경우=>  세션객체가 존재하지 않으면 null리턴함.
 	           
 	- 세션 삭제처리 방법
 	1. invalidate()메서드 호출
 	2. setMaxInactiveInterval(int interval)메서드 호출
 	  => 일정시간(초)동안 요청이 없으면 세션객체 삭제됨.
 	3. web.xml 에 <session-config> 설정하기 (분 단위)
 */
public class T06_ServletSessionTest {

}
