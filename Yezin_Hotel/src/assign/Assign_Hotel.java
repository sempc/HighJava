package assign;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Assign_Hotel {
	private Scanner scan = new Scanner(System.in);
	private Map<Integer, Hotel> hotelInfo = new HashMap<Integer, Hotel>();
		
	/*public Assign_Hotel() {
		scan = new Scanner(System.in);
		hotelI = new HashMap<Integer, Hotel>();
	}*/
	
	public void start() {
		System.out.println("***********************************");
		System.out.println("\t== HOTEL OPEN==");
		System.out.println("***********************************");
		fileExist();
		fileIn();
		while(true) {
			System.out.println("***********************************");
			System.out.println("\t1. CHECK IN");
			System.out.println("\t2. CHECK OUT");
			System.out.println("\t3. HOTEL INFO");
			System.out.println("\t4. HOTEL CLOSE");
			System.out.println("***********************************");
			System.out.println("ENTER THE NUMBER >>");
		
			int input = scan.nextInt();
			
			
			switch (input) {
				case 1:
					chin();
					break;
				case 2:
					chout();
					break;
				case 3:
					hotinfo();
					break;
				case 4:
					close();
					break;
				default:
					System.out.println("**ERROR : RE-ENTER**");
					break;
			}
		}
	}
	
	private void close() {
		System.out.println("THANK YOU!");
		System.out.println("TERMINATE PROGRAM");
		fileOut();
		/*hotel = false;*/
		System.exit(0);
	}
	private void hotinfo() {
		Set<Integer> keySet = hotelInfo.keySet();
		Iterator<Integer> it = keySet.iterator();
		System.out.println("**************사용중인 방**************");
		System.out.println("\t방번호 : 투숙객 이름");
		System.out.println("************************************");
		while(it.hasNext()) {
			int roomN = it.next();
			Hotel hi = hotelInfo.get(roomN);
			System.out.println("\t" + hi.getRoomN() + " : " + hi.getName());
		}
		/*for(int i=0; i<hotelI.size(); i++) {
			System.out.println("\t" + hotelI.get(hotelI) );
		}*/
		System.out.println("************************************");
		
	}

	private void chout() {

		System.out.println("어느방 체크아웃?");
		int roomN = scan.nextInt();
		
		if(hotelInfo.get(roomN) != null) {
			hotelInfo.remove(roomN);
			System.out.println("체크아웃 완료!");
		} else {
			System.out.println("체크인 한적 없음-체크아웃 불가능");
		}
	}

	private void chin() {
		System.out.println("\t401 402 403 404");
		System.out.println("\t301 302 303 304");
		System.out.println("\t201 202 203 204");
		System.out.println("\t101 102 103 104");

		
		System.out.println("어느방 체크인? >>");
		int roomN = scan.nextInt();

		
		
		if(hotelInfo.get(roomN) != null) {
			System.out.println("아직 체크아웃 안함-체크인 불가능");
			
		}else {
			System.out.println("투숙객 이름? >>");
			String name = scan.next();
			hotelInfo.put(roomN, new Hotel(roomN, name));
		}
		
		
		
	}
	
	public void fileExist() {
		File hotelFile = new File("d:/D_Other/hotelInfo.bin");
		
		if(hotelFile.exists()) {
			System.out.println(hotelFile.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(hotelFile.getAbsolutePath() + "은 없는 파일입니다.");
			
			try {
				if(hotelFile.createNewFile()) {
					System.out.println(hotelFile.getAbsolutePath()
						+ "파일을 새로 만들었습니다.");
				}
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void fileIn(){
		try {
			ObjectInputStream  ois = 
					new ObjectInputStream(
						new BufferedInputStream(
							new FileInputStream("d:/D_Other/hotelInfo.bin")));
			
			Object obj = null;
			while((obj = ois.readObject()) != null) {
				//마지막에 다다르면 EOF 예외 발생함
				Hotel hotelIn = (Hotel) obj;
				hotelInfo.put(hotelIn.getRoomN(), hotelIn);
			}
			
			ois.close();
		} catch (EOFException e2) {
			System.out.println("불러오기 완료");
		} catch (IOException|ClassNotFoundException e3) {
			e3.printStackTrace();
		}
	}
	
	public void fileOut() {
		try {
			//객체를 파일에 저장하기
			
			//출력용 스트림 객체 생성
			ObjectOutputStream oos = 
					new ObjectOutputStream(
						new BufferedOutputStream(
							new FileOutputStream("d:/D_Other/hotelInfo.bin")));
			
			//쓰기 작업
			
			Collection<Hotel> hotelOut = hotelInfo.values();
			for(Hotel hotel : hotelOut) {
				oos.writeObject(hotel);//직렬화			
			}
			
			System.out.println("쓰기(Output) 작업 완료");
			oos.close();
		}catch (FileNotFoundException e) {
			System.out.println("파일을 새로 만듭니다.");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Assign_Hotel().start();
	}
}
class Hotel implements Serializable{
	private int roomN;
	private String name;
	@Override
	public String toString() {
		return roomN + " : " + name;
	}
	public int getRoomN() {
		return roomN;
	}
	public void setRoomN(int roomN) {
		this.roomN = roomN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Hotel(int roomN, String name) {
		super();
		this.roomN = roomN;
		this.name = name;
	}
}