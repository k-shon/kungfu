package top.annokshon.kungfu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/*
 * 商品信息
 */
@Entity
@Table(name = "tb_goods")
public class Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 50)
	private String number; //商品编号
	@Column(length = 30)
	private String name;  //商品名称
	@Column(length = 10)
	private int price;  //商品价格
	@Column(length = 20)
	private String type;  //商品类型
	@Column(length = 10)
	private int weight;  //商品重量
	@Column(length = 10)
	private int texture;  //材质
	@Column(length = 10)
	private int repertory;  //库存
	@Column(length = 10)
	private int sell;  //销售量
	@Column(length = 10)
	private int collect;  //被收藏数量
	@Column(length = 10)
	private int cart;  //被加入购物车数量
	@ManyToOne
	private Picture picture;   //配图地址目录
	@ManyToOne
	private Store store;  //商城
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getTexture() {
		return texture;
	}
	public void setTexture(int texture) {
		this.texture = texture;
	}
	public int getRepertory() {
		return repertory;
	}
	public void setRepertory(int repertory) {
		this.repertory = repertory;
	}
	public int getSell() {
		return sell;
	}
	public void setSell(int sell) {
		this.sell = sell;
	}
	public int getCollect() {
		return collect;
	}
	public void setCollect(int collect) {
		this.collect = collect;
	}
	public int getCart() {
		return cart;
	}
	public void setCart(int cart) {
		this.cart = cart;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	private Date createtime;
}
