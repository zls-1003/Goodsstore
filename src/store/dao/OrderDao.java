package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import store.model.Order;
import store.util.DateUtil;
import store.util.StringUtil;

public class OrderDao {

	/**
	 * 添加订单信息
	 * @param con
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Order order) throws Exception {
		String sql = "insert into t_order values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, order.getNumber());
		pstmt.setString(2, DateUtil.formatDate(order.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
		pstmt.setInt(3, order.getGoods_id());
		pstmt.setInt(4, order.getCustomer_id());
		return pstmt.executeUpdate();
	}

	/**
	 * 查看订单信息
	 * @param con
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Order order) throws Exception {
		StringBuffer sb = new StringBuffer(" select o.id,o.number,o.addtime,o.goods_id,o.customer_id,c.name,f.name as tname from t_order o,t_customer c,t_goods f where o.customer_id=c.id and o.goods_id=f.id ");
		if (StringUtil.isNotEmpty(order.getName())) {
			sb.append(" and c.name like '%" + order.getName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 查看个人订单信息
	 * @param con
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public ResultSet listById(Connection con, Order order) throws Exception {
		String sql = " select * from t_order o ,t_goods f where o.goods_id=f.id and o.customer_id=? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, order.getCustomer_id());
		return pstmt.executeQuery();
	}
	
}
