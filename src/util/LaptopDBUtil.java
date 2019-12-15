package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LaptopDBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/codingmondb?"
				+ "useSSL=false"
				+ "&useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC";
		String user = "myjava";
		String password = "12345";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class가 존재하지 않음.");
		} catch (SQLException e) {
			System.out.println("DB 연결 정보가 일치하지 않음 : "+e.getMessage());
		}
		
		return conn;
	}
	
	// 자원해제
		public static void close(AutoCloseable... closer) {
			for(AutoCloseable a : closer) {
				try {
					if(a != null) {
						System.out.println(a.toString()+" close");
						a.close();
					}
				} catch (Exception e) {}
			}
		}
		
		// transaction commit
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
					System.out.println("commit 완료");
				}
			} catch (SQLException e) {
				System.out.println("commit 실패");
			}
		}
		
		// transaction roll back
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
					System.out.println("roll back 완료");
				}
			} catch (SQLException e) {
				System.out.println("roll back 실패");
			}
		}
}
