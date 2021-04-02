package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultichatClientHome {
	Scanner scan = new Scanner(System.in);
	private String nickName;//대화명
	
	//프로그램 시작
	public void startClient() {
		//대화명 입력받기
		System.out.print("대화명 >> ");
		nickName = scan.next();
		
		Socket socket = null;
		
		try {
			
			socket = new Socket("192.168.43.19",7777);
			
			System.out.println("서버에 연결되었습니다.");
			System.out.println("채팅을 보내는 방법은 원하는 말을 입력하고 엔터를 누르시면 됩니다^^");
			System.out.println("상대방만 확인하는 귓속말을 하는 방법은(/w 대화명 대화)를 하시면 됩니다~[형식엄수 필수!]");
			
			//송신용 스레드 생성
			ClientSender sender = new ClientSender(socket, nickName);
			
			//수신용 스레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			
			sender.start();
			receiver.start();
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	//송신용 Thread;메시지를 전송하는 스레드
	class ClientSender extends Thread {
		Socket socket;
		DataOutputStream dos;
		String name;
		Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket,String name) {
			this.socket = socket;
			this.name = name;
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				//시작하자 마자 자신의 대화명을 서버로 전송
				if(dos != null) {
					dos.writeUTF(name);
				}
				
				while(dos != null) {
					//키보드를 입력받은 메시지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}
				
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	
	//수신용 Thread;메시지를 받는 스레드
	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					//서버로 부터 수신한 메시지 출력하기
					System.out.println(dis.readUTF());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
	
		new MultichatClientHome().startClient();
		
	}
}































