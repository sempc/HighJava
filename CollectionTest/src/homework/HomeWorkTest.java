package homework;

import java.math.BigDecimal;

/*
 * 문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

예) 행성의 반지름(KM):
수성(2439), 
금성(6052), 
지구(6371), 
화성(3390), 
목성(69911), 
토성(58232), 
천왕성(25362), 
해왕성(24622)

(반지름*반지름)*3.14*4
 */

public class HomeWorkTest {
	
	public enum Planet { //열거행 선언
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 
		목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		
	
		private int pi;//괄호속 데이터값 저장할 변수

		Planet(int data) {//생성자 및 저장
			pi = data;
		}

		public int getPi() {//값 반환
			return pi;
		}
	}
	
	public static void main(String[] args) {
		
		Planet[] enumArr=Planet.values();
		for (int i = 0; i < enumArr.length; i++) {
			System.out.println(enumArr[i].name()+"의 반지름은 "+enumArr[i].getPi()+" 이고");
			
			int pp=enumArr[i].getPi();
		    double circle= 4*3.141592*(pp*pp);
			
		    System.out.println(enumArr[i].name()+"의 면적은 "+circle+" 입니다");
			System.out.println();
		}

	}
}
