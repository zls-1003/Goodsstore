package store.model;
//商品表
public class Goods {
	
	private Integer id;
	private String name;
	private Integer price;
	private Integer stock;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Goods(String name, Integer price, Integer stock) {
		super();
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	public Goods() {

	}
	public Goods(Integer stock) {
		super();
		this.stock = stock;
	}
	
}
