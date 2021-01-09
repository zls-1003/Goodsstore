package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import store.model.Goods;
import store.util.StringUtil;

public class GoodsDao {

	/**
	 * 添加商品信息
	 * @param con
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Goods goods) throws Exception {
		String sql = "insert into t_goods values(null,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, goods.getName());
		pstmt.setInt(2, goods.getPrice());
		pstmt.setInt(3, goods.getStock());

		return pstmt.executeUpdate();
	}

	/**
	 * 查看商品信息
	 * @param con
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Goods goods) throws Exception {
		StringBuffer sb = new StringBuffer("  select * from t_goods ");
		if (StringUtil.isNotEmpty(goods.getName())) {
			sb.append(" and name like '%" + goods.getName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * 删除商品信息
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, String id) throws Exception {
		String sql = " delete from t_goods where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	/**
	 * 更新商品信息
	 * @param con
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Goods goods) throws Exception {
		String sql = " update t_goods set name=?,price=?,stock=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, goods.getName());
		pstmt.setInt(2, goods.getPrice());
		pstmt.setInt(3, goods.getStock());

		pstmt.setInt(4, goods.getId());
		return pstmt.executeUpdate();
	}
	public int updateById(Connection con, Goods goods) throws Exception {
		String sql = " update t_goods set stock=stock-1 where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, goods.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 获取商品库存
	 * @param con
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public Goods getGoodsStock(Connection con, Goods goods) throws Exception {
		Goods result = null;
		String sql = " select * from t_goods where id=?";// 查找数据库
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, goods.getId());
		
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			result = new Goods();
			result.setId(rs.getInt("id"));
			result.setStock(rs.getInt("stock"));
		}
		return result;
	}
	
}
