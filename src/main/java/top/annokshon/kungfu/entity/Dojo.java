package top.annokshon.kungfu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
/*
 * 武馆信息
 */
@Entity
@Table(name = "tb_dojo")
public class Dojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 50)
	private String name;  //武馆名称
	@Column(length = 20)
	private String type;  //武馆类别
	@Column(length = 10)
	private int price;  //武馆报名价格
	@Column(length = 200)
	private String description;  //武馆简介
	@Column(length = 20)
	private String phone; //武馆联系电话
	@Column(length = 20)
	private int status; //武馆状态(0:冻结 1:激活)
	@ManyToOne
	private Picture picture; //武馆配图地址目录
	@ManyToOne
	private Person person;  //武馆负责人
	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	private Date createtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
