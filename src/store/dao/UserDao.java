package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import store.model.User;

public class UserDao {

	/**
	 * 管理员登录DAO
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception {
		User resultUser = null;
		String sql = "select * from t_user where account=? and password=?";// 查找数据库
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getAccount());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setAccount(rs.getString("account"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}

	/**
	 * 添加管理员
	 * 
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, User user) throws Exception {
		String sql = " insert into t_user values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getAccount());
		pstmt.setString(2, user.getPassword());
		return pstmt.executeUpdate();
	}

}
