package kr.or.dgit.bigdata.school.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final ConnectionFactory instance = new ConnectionFactory();

	public static Connection getConnection() {
		return instance.createConnection();
	}

	private ConnectionFactory(){}
	
	private Connection createConnection(){
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/school";
		String user = "user_school";
		String pwd = "rootroot";
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			System.err.println("JDBC Driver 추가 혹은 url, user, password 확인");
			e.printStackTrace();
		}
		return con;
	}
	
}
