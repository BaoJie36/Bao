package yaopin;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Sql1 {

	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbURL;
	private static String userName;
	private static String userPwd;

	
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public static Connection getCoonection() {
		dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName= yaopinDB1" ;
		userName = "admin";
		userPwd = "123";
	
		try {
			Connection con = DriverManager.getConnection(dbURL, userName, userPwd);
			return con;
		}catch (Exception e) {
			
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "<html><div style='width: 300px;'>" + e + "</div></html>",
					"连接错误信息", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public static ResultSet executeQuery(String SQL) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = getCoonection();
			stmt = conn.createStatement();//创建一个 Statement对象，用于将SQL语句发送到数据库。
			rs = stmt.executeQuery(SQL);

			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	public static boolean executeUpdate(String SQL) {
		Connection conn = null;
		Statement stmt = null;
		int result;
		try {
			conn = getCoonection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(SQL);
			if (result > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return false;
	}
	
}