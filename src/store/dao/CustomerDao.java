package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import store.model.Customer;
import store.util.StringUtil;

public class CustomerDao {
	
	/**
	 * 添加用户信息
	 * @param con
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Customer customer) throws Exception {
		String sql = "insert into t_customer values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, customer.getAccount());
		pstmt.setString(2, customer.getPassword());
		pstmt.setString(3, customer.getName());
		pstmt.setString(4, customer.getPhone());

		return pstmt.executeUpdate();
	}

	/**
	 * 查看用户信息
	 * @param con
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Customer customer) throws Exception {
		StringBuffer sb = new StringBuffer("  select * from t_customer ");
		if (StringUtil.isNotEmpty(customer.getName())) {
			sb.append(" and name like '%" + customer.getName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * 删除用户信息
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, String id) throws Exception {
		String sql = "delete from t_customer where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	/**
	 * 更新用户信息
	 * @param con
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Customer customer) throws Exception {
		String sql = " update t_customer set account=?,password=?,name=?,phone=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, customer.getAccount());
		pstmt.setString(2, customer.getPassword());
		pstmt.setString(3, customer.getName());
		pstmt.setString(4, customer.getPhone());
		pstmt.setInt(5, customer.getId());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 用户登录
	 * @param con
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public Customer login(Connection con, Customer customer) throws Exception {
		Customer resultCustomer = null;
		String sql = " select * from t_customer where account=? and password=?";// 查找数据库
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, customer.getAccount());
		pstmt.setString(2, customer.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultCustomer = new Customer();
			resultCustomer.setId(rs.getInt("id"));
			resultCustomer.setAccount(rs.getString("account"));
			resultCustomer.setPassword(rs.getString("password"));
		}
		return resultCustomer;
	}
}
