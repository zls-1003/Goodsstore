package store.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtil{

	private String dbUrl="jdbc:mysql://localhost:3306/store?characterEncoding=utf8&serverTimezone=UTC";//数据库连接地址
	private String dbUserName="root"; // 用户名
	private String dbPassword="123456"; // 密码
	private String jdbcName="com.mysql.jdbc.Driver"; // 驱动名称
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
