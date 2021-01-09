package store.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import store.dao.CustomerDao;
import store.model.Customer;
import store.util.DbUtil;
import store.util.StringUtil;

@SuppressWarnings("serial")
public class CustomerAdd extends JInternalFrame {
	
	/**
	 * 管理员登录  用户添加
	 */
	private DbUtil dbUtil = new DbUtil();
	
	private CustomerDao customerDao = new CustomerDao();
	
	private JTextField txt_account;
	private JTextField txt_pass;
	private JLabel label_1;
	private JTextField txt_name;
	private JLabel label_2;
	private JTextField txt_phone;
	private JButton btnNewButton;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerAdd frame = new CustomerAdd();
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
	public CustomerAdd() {
		setTitle("用户添加");
		setClosable(true);
		setBounds(100, 100, 554, 436);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		txt_account = new JTextField();
		txt_account.setFont(new Font("宋体", Font.PLAIN, 18));
		txt_account.setColumns(10);
		
		JLabel label = new JLabel("密码");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		txt_pass = new JTextField();
		txt_pass.setFont(new Font("宋体", Font.PLAIN, 18));
		txt_pass.setColumns(10);
		
		label_1 = new JLabel("姓名");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		txt_name = new JTextField();
		txt_name.setFont(new Font("宋体", Font.PLAIN, 18));
		txt_name.setColumns(10);
		
		label_2 = new JLabel("电话");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		txt_phone = new JTextField();
		txt_phone.setFont(new Font("宋体", Font.PLAIN, 18));
		txt_phone.setColumns(10);
		
		btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer(e);
			}


		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		
		button = new JButton("重置");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAction(e);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txt_account, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(14)
									.addComponent(txt_pass, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(142)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(74)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(txt_pass, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(txt_account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	/**
	 * 添加操作
	 * @param et
	 */
	private void addCustomer(ActionEvent et) {
		
		String account = this.txt_account.getText();
		String password = this.txt_pass.getText();
		String name = this.txt_name.getText();
		String phone = this.txt_phone.getText();
		
		if (StringUtil.isEmpty(account)) {
			JOptionPane.showMessageDialog(null, "账号不能为空");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if (StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "姓名不能为空");
			return;
		}
		if (StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "电话号码不能为空");
			return;
		}
		
		Customer customer = new Customer( account,  password,  name,  phone);
		
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int n = customerDao.add(con, customer);
			if (n == 1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				resetNull();
			} else {
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败");
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void resetAction(ActionEvent e) {
		
		resetNull();
	}
	
	/**
	 * 置空操作
	 */
	private void resetNull(){
		this.txt_account.setText("");
		this.txt_pass.setText("");
		this.txt_name.setText("");
		this.txt_phone.setText("");
	}
	

}
