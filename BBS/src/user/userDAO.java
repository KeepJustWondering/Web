package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public userDAO() {
		try {
			String dbURL ="jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID="scott";
			String dbPassword = "tiger";
			String driver = "oracle.jdbc.driver.OracleDriver";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		public int login(String userID, String userPassword) {
			String SQL = "SELECT userPassword FROM user1 WHERE userID = ?";
			try {
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString(1).equals(userPassword))
						return 1;
					else 
						return 0;
				}
				return -1;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return -2;//데이터 베이스 오류
		}
	}

