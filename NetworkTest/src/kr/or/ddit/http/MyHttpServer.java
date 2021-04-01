package kr.or.ddit.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 간단한 웹서버 예제
 */
public class MyHttpServer {
	
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	/**
	 * 응답 헤더 생성하기
	 * @param contentLength 응답내용 크기
	 * @param mimeType 마임타입
	 * @return 바이트 배열
	 */
	private byte[] makeResponseHeader(int contentLength, 
										String mimeType) {
		String header = "HTTP/1.1 200 OK\r\n"
					+ "Server: MyHTTPServer 1.0\r\n"
					+ "Content-length: " + contentLength + "\r\n"
					+ "Content-type: " + mimeType + "; charset="
					+ this.encoding + "\r\n\r\n";
		
		return header.getBytes();
	}
	
	private byte[] makeResponseBody(String filePath) 
						throws IOException {
		FileInputStream fis = null;
		byte[] data = null;
		try {
			File file = new File(filePath);
			data = new byte[(int)file.length()];
			
			fis = new FileInputStream(file);
			fis.read(data);
		}finally {
			if(fis != null) {
				fis.close();
			}
		}
		return data;
	}
	
}
