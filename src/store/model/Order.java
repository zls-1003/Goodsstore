package store.model;
//订单表
import java.util.Date;

public class Order {

	private Integer id;
	private String number;
	private Date addtime;
	private Integer goods_id;
	private Integer customer_id;
	private String name;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Order(String number, Date addtime, Integer goods_id, Integer customer_id) {
		super();
		this.number = number;
		this.addtime = addtime;
		this.goods_id = goods_id;
		this.customer_id = customer_id;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
}
