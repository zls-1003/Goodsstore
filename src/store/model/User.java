package store.model;
//管理员表
public class User {

	private Integer id;
	private String account;
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
}
