package kr.co.green.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	// DB 연결
		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // "패키지명.클래스명"
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // ~:ip:port:db정보
		private static final String USER = "WEBADMIN";
		private static final String PWD = "a980728";
		private Connection con; // Connection은 인터페이스이다.
		

		// DB 연결해주는 메소드
		public Connection connDB() { // Connection 인터페이스를 사용하여 어떤 데이터베이스에 연결할 지 설정
										// 한 후 DAO클래스로 Connect의 객체를 넘김으로써 실질적인 연결을 DAO클래스가 담당한다.
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PWD);
				return con;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
}