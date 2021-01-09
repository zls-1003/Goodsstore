package store.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import store.dao.OrderDao;
import store.model.Order;
import store.util.DbUtil;

@SuppressWarnings("serial")
public class LookMyOrder extends JInternalFrame {
	
	/**
	 * 用户登录 查看个人订单
	 */
	private DbUtil dbUtil = new DbUtil();
	
	private OrderDao orderDao = new OrderDao();
	
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LookMyOrder frame = new LookMyOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LookMyOrder() {
		setClosable(true);
		setTitle("查看个人订单");
		setBounds(100, 100, 645, 457);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(87, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"订单编号", "订单时间", "商品名称", "商品单价"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		this.fillTable(new Order());

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fillTable(Order order){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		order.setCustomer_id(Integer.parseInt(Login.getLogin_id()));
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs = orderDao.listById(con, order);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("number"));
				v.add(rs.getString("addtime"));
				v.add(rs.getString("name"));
				v.add(rs.getString("price"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
