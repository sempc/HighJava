package util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * SqlMapClient 객체를 제공하는 클래스
 */
public class SqlMapClientUtil {
	private static SqlMapClient smc;
	
	private SqlMapClientUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static SqlMapClient getInstance(){
		if(smc == null) {
			try {
				// 1-1. xml 설정문서 읽어오기
				Charset charset = Charset.forName("UTF-8"); //설정파일 인코딩
				Resources.setCharset(charset);
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// 1-2/. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close();//Reader 객체 닫기
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		return smc;
	}
}
