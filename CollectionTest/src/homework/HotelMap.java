package homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelMap {
	
	Scanner scanner;
	private Map<String, HotelVO> hotelMap;
	
	public HotelMap() {
		scanner = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	
	private void hotelMain() {
		System.out.println("************************************");
		System.out.println("\t호텔 문을 열었습니다.");
		System.out.println("************************************");
		while (true) {
			System.out.println("************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.종료");
			System.out.println("************************************");
			System.out.print("번호 입력 >");
			int menuNum = scanner.nextInt(); 
			scanner.nextLine();
			switch (menuNum) {
			case 1:
				checkIn(); 
				break;
			case 2:
				checkOut(); 
				break;
			case 3:
				roomState(); 
				break;
			case 4:
				System.out.println("프로그램을 종료합니다...");
				System.exit(0);
				break;
			default:
				System.out.println("숫자를 잘못 입력하셨습니다. 다시입력하세요.");
				return;
			} 
		}
	}	

	private void roomState() {
		Set<String> keySet = hotelMap.keySet();
		System.out.println("=====================================");
		System.out.println(" 번호\t객실번호\t투숙객 이름 ");
		System.out.println("=====================================");
		
		if(keySet.size() == 0) {
			System.out.println("등록된 룸이 없습니다");
		} else {
			Iterator<String> it = keySet.iterator();			
			int count = 0;
			while(it.hasNext()) {
				count++;
				String rNum = it.next();
				HotelVO h = hotelMap.get(rNum);
				System.out.println(" " + count + "번"+ "\t" + h.getrNum() +"\t" + h.getName());
			}
		}
		System.out.println("================================");
	}

	private void checkIn() {
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 입력 > ");
		String rNum = scanner.next();
		
		if(hotelMap.get(rNum) != null) {
			System.out.println("죄송합니다. 다른 손님이 방을 사용중입니다.");
			return;
		} else {
			System.out.println("성함이 어떻게 되십니까?");
			System.out.print("이름 입력 => ");
			String name = scanner.next();
			
			hotelMap.put(rNum, new HotelVO(rNum, name));
			System.out.println(name + " 님 체크인 준비가 완료되었습니다.");
		}
	}

	private void checkOut() {
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("객실 번호 > ");
		String rNum = scanner.next();
		if(hotelMap.remove(rNum) == null) {
			System.out.println(rNum + " 번 방에 사용 중인 고객님은 없습니다.");
		} else {
			System.out.println("체크아웃하셨습니다. 저희 호텔을 이용해주셔서 감사합니다.");
			System.out.println("좋은 하루 되십시오. 고객님.");
		}
	}




	public static void main(String[] args) {
	
		HotelMap hm = new HotelMap();
		hm.hotelMain();
	}
}


/**
 * 고객의 정보를 저장하기 위한 VO클래스
 */
class HotelVO{
	private String rNum;
	private String name;
	
	public HotelVO(String rNum, String name) {
		super();
		this.name = name;
		this.rNum = rNum;
	}
	
	public String getrNum() {
		return rNum;
	}


	public void setrNum(String rNum) {
		this.rNum = rNum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Hotel [rNum=" + rNum + ", name=" + name + "]";
	}
}