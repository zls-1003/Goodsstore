package store.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import store.dao.CustomerDao;
import store.dao.UserDao;
import store.model.Customer;
import store.model.User;
import store.util.DbUtil;
import store.util.StringUtil;

@SuppressWarnings("serial")
public class Login extends JFrame {
	
	/**
	 * 登录界面
	 */
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private CustomerDao customerDao = new CustomerDao();

	private JPanel contentPane;
	private JTextField txt_name;
	private JPasswordField txt_password;
	private JRadioButton radio_user;
	private JRadioButton radio_admin;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private static String login_id ;
	private static String login_account ;
	private static String login_password ;

	/**
	 * 启动应用程序
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建框架.
	 */
	public Login() {
		setTitle("超市管理系统");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		txt_name = new JTextField();
		txt_name.setFont(new Font("宋体", Font.PLAIN, 18));
		txt_name.setColumns(10);
		
		JLabel label = new JLabel("密码");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		txt_password = new JPasswordField();
		
		JLabel label_1 = new JLabel("身份");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		 radio_user = new JRadioButton("用户");
		radio_user.setSelected(true);
		buttonGroup.add(radio_user);
		radio_user.setFont(new Font("宋体", Font.PLAIN, 18));
		
		 radio_admin = new JRadioButton("管理员");
		buttonGroup.add(radio_admin);
		radio_admin.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton btnNewButton = new JButton("登陆");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction(e);
			}

		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(txt_password))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(radio_user)
									.addGap(18)
									.addComponent(radio_admin, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))))
					.addGap(103))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txt_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(radio_user)
						.addComponent(radio_admin))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		contentPane.setLayout(gl_contentPane);
		// 设置登录窗口居中显示
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * 用户登录
	 * @param e
	 */
	private void loginAction(ActionEvent es) {
		String account = txt_name.getText();
		String password = new String(txt_password.getPassword());

		if (StringUtil.isEmpty(account)) {
			JOptionPane.showMessageDialog(null, "账号不能为空");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		
		//管理员登录
		boolean adminLogin = this.radio_admin.isSelected();
		//用户登录
		boolean userLogin = this.radio_user.isSelected();
		
		User user = new User(account, password);
		Customer customer = new Customer(account, password);
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if(userLogin){
				Customer currentCustomer = customerDao.login(con, customer);
				if (currentCustomer != null) {
					login_id = String.valueOf(currentCustomer.getId()) ;
					login_account = currentCustomer.getAccount();
					login_password = currentCustomer.getPassword();
					
					dispose();
					
					new CustomerMain().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				}
			}else if(adminLogin){
				User currentUser = userDao.login(con, user);
				if (currentUser != null) {
					login_id = String.valueOf(currentUser.getId()) ;
					login_account = currentUser.getAccount();
					login_password = currentUser.getPassword();
					dispose();
					new MainView().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				}
			}else{
				JOptionPane.showMessageDialog(null, "没有操作权限");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "登录失败");
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con); // 关闭数据库
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static String getLogin_id() {
		return login_id;
	}

	public static void setLogin_id(String login_id) {
		Login.login_id = login_id;
	}

	public static String getLogin_account() {
		return login_account;
	}

	public static void setLogin_account(String login_account) {
		Login.login_account = login_account;
	}

	public static String getLogin_password() {
		return login_password;
	}

	public static void setLogin_password(String login_password) {
		Login.login_password = login_password;
	}
	
	
}
