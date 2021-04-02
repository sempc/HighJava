package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultichatServerHome {
	//대화명,클라이언트의 Socket을 저장하기 위한 Map변수 선언
	Map<String , Socket> clients;
	
	//생성자
	public MultichatServerHome() {
		//동기화 처리가 가능하도록 Map객체 생성
		clients = Collections.synchronizedMap(new HashMap<String,Socket>());
	}
	
	
	//서버 시작 
	public void startServer() {
		
		Socket socket = null;
		
		try(ServerSocket serverSocket = new ServerSocket(7777)){ //여기서 만들어진 객체는 자동으로 클로즈를 해준다,따로 해줄필요가없다
			
			System.out.println("서버가 시작되었습니다.");
		
			while(true) {
				//클라이언트의 접속을 대기한다.
				socket = serverSocket.accept();
				
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] 에서 접속하였습니다.");
				
				//메세지를 전송 처리하는 스레드 생성 및 실행
				
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
				
			}
		
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 안내메세지를 전송하는 메서드 ; 이건 전체인원의 알람같은것
	 * @param msg
	 */
	public void sendMessage(String msg) {
		//Map에 저장된 유저의 대화명 리스트 추출(key값 구하기)
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {//클라이언트 수 만큼 돈다==>메세지를 보내준다.
			try {
				String name = it.next();//대화명
				
				//대화명에 해당하는 Socket의 OutputStream 구하기
				DataOutputStream dos= new DataOutputStream(clients.get(name).getOutputStream());
				//클라이언트의 밸류값(서버주소)을 가지고 와서  보내기위해
				
				dos.writeUTF(msg);//메세지 보내기
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 안내메세지를 전송하는 메서드
	 * @param msg
	 * @param from
	 */
	
	public void sendMessage(String msg, String from) {	
		//Map에 저장된 유저의 대화명 리스트 추출(key값 구하기)
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {//클라이언트 수 만큼 돈다==>메세지를 보내준다.
			try {
				String name = it.next();//대화명
				
				//대화명에 해당하는 Socket의 OutputStream 구하기
				DataOutputStream dos= new DataOutputStream(clients.get(name).getOutputStream());
				//클라이언트의 밸류값(서버주소)을 가지고 와서  보내기위해
				
				dos.writeUTF("["+from+"]"+msg);//메세지 보내기
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		 }
		
		
		
		
	}
//--------------------------------------------------------------------------------------------------------------------	

	public void sendMessage(String msg, String toname, String from) {
		//Map에 저장된 유저의 대화명 리스트 추출(key값 구하기)
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {//클라이언트 수 만큼 돈다==>메세지를 보내준다.
			try {
				String name = it.next();//대화명
				if(toname.equals(name)) {
				
				DataOutputStream dos= new DataOutputStream(clients.get(toname).getOutputStream());
				
				dos.writeUTF("["+from+"]"+msg);//메세지 보내기
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
//--------------------------------------------------------------------------------------------------------------------	
	
	
	
	
		
	//서버에서 클라이언트로 메세지를 전송할 스레드를 Inner클래스로 정의
	//=> Inner클래스에서는 부모 클래스의 멤버들을 직접 사용할수 있다.(인스턴스일수(객체) 있고 메서드일수도있다)
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket; //들어오는 사람만큼 서버리시버의 객체를 만든다.
			
			try {
				//수신용
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		
		@Override
		public void run() {
			
			try {
				//서버에서는 클라이언트가 보내는 최초의 메세지 즉,대화명을 수신해야 한다
				name = dis.readUTF();
				
				//대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메세지를 보낸다
				sendMessage("#"+name+"님이 입장했습니다.");
				
				//대화명과 소켓정보를 Map에 저장한다
				clients.put(name, socket);
				System.out.println("현재 서버 접속자 수는 "+clients.size()+"명 입니다.");
				
				//이 이후의 메세지 처리는 반복문으로 처리한다
				//한 클라이언트가 보낸 메세지를 다른 모든 클라이언트에게 보내준다
				
				while(dis != null) {
					String msg = dis.readUTF();
					String[] array = msg.split(" ",3);
					if(array[0].equals("/w")){
						Iterator<String> it = clients.keySet().iterator();
						while(it.hasNext()) {//클라이언트 수 만큼 돈다==>메세지를 보내준다.
							String toname = it.next();//대화명
							if (array[1].equals(toname)) {
								sendMessage(array[2],array[1],name);
							}
						}
						
					}else {
						
						sendMessage(dis.readUTF(),name);
						
					}
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}finally {//무한루프인데 여기서 사람이 나가면 예외처리되어 finall로 오게된다.
				//이 finally영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다
				sendMessage(name+"님이 나가셨습니다");
				
				//Map에서 해당 대화명을 삭제한다
				clients.remove(name);
				
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] 에서 접속을 종료했습니다.");
				System.out.println("현재 접속자 수는 "+clients.size()+"명 입니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		new MultichatServerHome().startServer();
	}
}





























