package top.annokshon.kungfu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/*
 * 个人信息
 */
@Entity
@Table(name = "kf_person")
@Data
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kf_id")
	private int id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kf_user_id")
	private User user;  //用户信息
	@Column(length = 100,name = "kf_nickname")
	private String nickname;  //真实姓名
	@Column(length = 300,name = "kf_avatar")
	private String avatar;  //头像地址
	@Column(length = 100,name = "kf_sex")
	private String sex; //性别
	@Column(length = 100,name = "kf_age")
	private String age; //年龄
	@Column(length = 100,name = "kf_email")
	private String email; //邮箱
	@Column(length = 100,name = "kf_mobile_number")
	private String mobileNumber;  //移动电话号码
	@Column(length = 100,name = "kf_idcard")
	private String idcard; //身份证
	@Column(length = 100,name = "kf_state")
	private String state; //状态
	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	@Column(name = "kf_modify_time")
	private Date modifyTime;  //修改时间

}
