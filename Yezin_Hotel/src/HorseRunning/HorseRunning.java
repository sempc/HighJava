package HorseRunning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HorseRunning {
/**
    3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를
        나타내는 프로그램 작성하기  
*/	
	// 순위 저장용 변수 선언
	static String strRank = "";
	static int RankList =0;
	static int intRank =0;
	
	public static void main(String[] args) {
		List<Horse> HorseList = new ArrayList<Horse>();
		HorseList.add(new Horse("1번마",1));
		HorseList.add(new Horse("2번마",1));
		HorseList.add(new Horse("3번마",1));
		HorseList.add(new Horse("4번마",1));
		HorseList.add(new Horse("5번마",1));
		HorseList.add(new Horse("6번마",1));
		HorseList.add(new Horse("7번마",1));
		HorseList.add(new Horse("8번마",1));
		HorseList.add(new Horse("9번마",1));
		HorseList.add(new Horse("0번마",1));

		
		
		for(Horse hs : HorseList) {
			hs.start();	
		}
		
		for(Horse hs : HorseList) {
			try {
				hs.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝...");
		System.out.println("---------------------");
		System.out.println();
		System.out.println(" 경기 결과 ");
		System.out.println("=================전역변수로정렬===================");
		System.out.println("       1등    2등    3등    4등    5등    6등    7등    8등    9등   10등");
		System.out.println("말이름 : " + strRank);
		System.out.println("=================compare정렬전===================");		
		for(Horse hs : HorseList) {
			System.out.println(hs.getNm() + " : " + hs.getRank() + "등");
		}
		System.out.println("=================compare정렬후===================");
		Collections.sort(HorseList, new Sortrank());
		for(Horse hs : HorseList) {
			System.out.println(hs.getNm() + " : " + hs.getRank()+ "등");
		}
	}
}
class Sortrank implements Comparator<Horse>{
	
	@Override
	public int compare(Horse hs1, Horse hs2) {
		if(hs1.getRank()>hs2.getRank()) {
			return 1;
		}else {
			return -1;
		}
//		return new Integer(stu1.getSum())
//				.compareTo(stu2.getSum())*-1;
	}
	
}
// 대문자 출력하는 스레드 클래스
class Horse  extends Thread implements Comparable<Horse>{
	private String nm;
	private int rank;
	
	// 생성자
	public Horse(String nm, int rank) {
		this.nm = nm;
		this.rank = rank;
	}
	
	
	public String getNm() {
		return nm;
	}


	public void setNm(String nm) {
		this.nm = nm;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	//♘//
	@Override
	public void run() {
		for(int i=0; i<50; i++) {
			print(i,getNm());
			
			try {
				// sleep()메서드의 값을 200~500 사이의 난수로 한다.
				Thread.sleep((int)(Math.random() * 20 + 10));
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(nm + " 도착...");
		HorseRunning.strRank += nm + " ";
		HorseRunning.RankList +=1;
		rank = HorseRunning.RankList;
		
	}
	private void print(int i, String name2) {
		String result ="";
		try {
		result +=name2;
		for (int j = 0; j <50 ; j++) {
			if(i==j) {
				result +="♘>";
			}else {
				result +="-";
			}
		}
		System.out.println(result);
	
		
		Thread.sleep(50);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int compareTo(Horse o) {
		return 0;
	}
	


	
}
