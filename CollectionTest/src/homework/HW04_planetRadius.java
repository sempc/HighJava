package homework;

import java.util.Scanner;

/*
 * 문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오. (단, enum 객체 생성시 반지름을 이용하도록 정의하시오.)
 * 
 * 예) 행성의 반지름(KM): 수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232),
 * 천왕성(25362), 해왕성(24622)
 */
public class HW04_planetRadius {
	static Scanner sc = new Scanner(System.in);
	enum planet{
		수성(2439), 금성(6052), 지구(6371), 
		화성(3390), 목성(69911), 토성(58232),
		천왕성(25362), 해왕성(24622);
				

		private int radius;
		
		planet(int radius) {
			this.radius = radius;
		}
		
		int getList() {
			return radius;
		}
		
		}; //e.planet end


	
	public static void main(String[] args) {
		while(true) {
			planet[] enumArr = planet.values();
			for(int i=0; i<enumArr.length; i++) {
				System.out.print(enumArr[i].name()+"\t");
			}
			System.out.println();
			System.out.println("면적을 구하고 싶은 행성의 이름을 입력해주세요.");
			String input; 
			System.out.println();
			input= sc.nextLine();
			if(input.equals("수성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[0].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("금성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[1].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("지구")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[2].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("화성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[3].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("목성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[4].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("토성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[5].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("천왕성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[6].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("해왕성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[7].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			if(input.equals("수성")) {
				System.out.println(input +"의 면적은 "+(4*Math.PI*Math.pow(enumArr[8].getList(),2))+"Km입니다.");
				input= sc.nextLine();
			}
			
			
			else {
				System.out.println("다시 입력해주세요");
			}
			
			
		}
		
}
}
