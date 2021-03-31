package kr.or.ddit.basic;
/**
 * Comparable 및 Comparator 예제
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04_ListSortTest {
	
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "이순신", "010-4444-4444"));
		memList.add(new Member(6, "강감찬", "010-5555-5555"));
		memList.add(new Member(2, "일지매", "010-6666-6666"));
		
		Member mem2 = new Member();
		System.out.println(mem2.toString());
		
		System.out.println("정렬전 : ");
		for(Member mem: memList) {
			System.out.println(mem);
		}
		System.out.println("===================");
		
		Collections.sort(memList); // 정렬하기
		
		System.out.println("이름의 오름차순으로 정렬 후 ");
		for(Member mem: memList) {
			System.out.println(mem);
		}
		System.out.println("===================");
		
		// 외부 정렬 기준을 이용한 정렬하기
		Collections.sort(memList, new SortNumDesc());
		System.out.println("번호의 내림차순으로 정렬 후 ");
		for(Member mem: memList) {
			System.out.println(mem);
		}
		System.out.println("===================");
	}
	
}

/**
 *  Member의 번호(num)의 내림차순으로 정렬하기
 */
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		/*if(mem1.getNum() > mem2.getNum()) {
			return -1;
		}else if(mem1.getNum() == mem2.getNum()) {
			return 0;
		}else {
			return 1;
		}
		*/
		
		// wrapper클래스에서 제공되는 메서드를 이용하는 방법1
		// 내림차순인 경우에는 -1을 곱해준다.
		return new Integer(mem1.getNum())
				.compareTo(mem2.getNum())* -1;
	}
	
}


class Member implements Comparable<Member> {
	
	public int num;	// 번호
	public String name;// 이름
	public String tel;	// 전화번호
	
	public Member() {
		//this(5, "홍길순", "343333");
	}
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	/**
	 * 이름을 기준으로 오름차순 정렬이 되도록 설정한다.
	 */
	@Override
	public int compareTo(Member mem) {
		
		return this.getName().compareTo(mem.getName());
	}
	
}
