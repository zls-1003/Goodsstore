package store.model;
//顾客表
public class Customer {

	private Integer id;
	private String account;
	private String password;
	private String name;
	private String phone;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;	}

	public Customer(String account, String password, String name, String phone) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	
	

}
