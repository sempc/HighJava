package HorseRunning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HorseRunning1 {
/**
    3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를
        나타내는 프로그램 작성하기  
*/	
	// 순위 저장용 변수 선언
	static String strRank = "";
	static int RankList =0;
	static int intRank =0;
	static List<Horse1> HorseList =new ArrayList<Horse1>();
	static List<Integer> Running=new ArrayList<>();
	public static void main(String[] args) {

//		List<Horse1> HorseList = new ArrayList<Horse1>();
//		List<Integer> Running = new ArrayList<>();
		HorseList.add(new Horse1("0번마",1));
		Running.add(0);
		HorseList.add(new Horse1("1번마",1));
		Running.add(0);
		HorseList.add(new Horse1("2번마",1));
		Running.add(0);
		HorseList.add(new Horse1("3번마",1));
		Running.add(0);
		HorseList.add(new Horse1("4번마",1));
		Running.add(0);
		HorseList.add(new Horse1("5번마",1));
		Running.add(0);
		HorseList.add(new Horse1("6번마",1));
		Running.add(0);
		HorseList.add(new Horse1("7번마",1));
		Running.add(0);
		HorseList.add(new Horse1("8번마",1));
		Running.add(0);
		HorseList.add(new Horse1("9번마",1));
		Running.add(0);

		
		for(Horse1 hs : HorseList) {
			hs.start();	
		}
		
		for(int i=0; i<55; i++) {
			
			try {
				
					for (Horse1 hs : HorseList) {
						String result ="";
						result +=hs.getNm();
						for (int j = 0; j < 50; j++) {
						if(j==Running.get(Integer.parseInt(hs.getNm().substring(0,1)))) {
							result +="♘>";
						}else {
							result +="-";
						}
					}
					System.out.println(result);
				}
				System.out.println();
				Thread.sleep(200);
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
		for(Horse1 hs : HorseList) {
			System.out.println(hs.getNm() + " : " + hs.getRank() + "등");
		}
		System.out.println("=================compare정렬후===================");
		Collections.sort(HorseList, new Sortrank1());
		for(Horse1 hs : HorseList) {
			System.out.println(hs.getNm() + " : " + hs.getRank()+ "등");
		}
	}
}
class Sortrank1 implements Comparator<Horse1>{
	
	@Override
	public int compare(Horse1 hs1, Horse1 hs2) {
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
class Horse1  extends Thread implements Comparable<Horse1>{
	private String nm;
	private int rank;
	
	// 생성자
	public Horse1(String nm, int rank) {
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
		int a = Integer.parseInt(this.nm.substring(0,1));
		for(int i=0; i<50; i++) {
			HorseRunning1.Running.add(a,i);
			
			try {
				// sleep()메서드의 값을 200~500 사이의 난수로 한다.
				Thread.sleep((int)(Math.random() * 200 + 100));
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(nm + " 도착...");
		HorseRunning1.strRank += nm + " ";
		HorseRunning1.RankList +=1;
		rank = HorseRunning1.RankList;
		
	}
	@Override
	public int compareTo(Horse1 o) {
		return 0;
	}
	


	
}
