package top.annokshon.kungfu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/*
 * 用户登录信息
 */
@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20)
	private String username;  //用户名
	@Column(length = 30)
	private String password;  //密码
	@ManyToOne
	private Person person;   //个人信息
	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	private Date createtime;
	
	public User(){}
	public User(String username,String password){
		this.username = username;
		this.password = password;
		this.createtime = new Date();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
}
