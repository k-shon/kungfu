package top.annokshon.kungfu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/*
 * 个人信息
 */
@Entity
@Table(name = "tb_person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Picture picture;  //头像
	@Column(length = 30)
	private String realName;  //真实姓名
	@Column(length = 10)
	private String sex; //性别
	@Column(length = 200)
	private String email; //邮箱
	@Column(length = 20)
	private String phone;  //手机
	@Column(length = 30)
	private String idcard; //身份证
	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	private Date createtime;  //创建时间

	public Person(){}
	public Person(String name,String idcard){
		this.realName = name;
		this.idcard = idcard;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
