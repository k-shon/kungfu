package top.annokshon.kungfu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
/*
 * 武馆信息
 */
@Data
@Entity
@Table(name = "kf_dojo")
public class Dojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kf_id")
	private int id;
	@Column(length = 100,name = "kf_dojo_name")
	private String dojoName;  //武馆名称
	@Column(length = 100,name = "kf_dojo_type")
	private String dojoType;  //武馆类别
	@Column(length = 100,name = "kf_dojo_apply_price")
	private int dojoApplyPrice;  //武馆报名价格
	@Column(length = 500,name = "kf_dojo_intro")
	private String dojoIntro;  //武馆简介
	@Column(length = 100,name = "kf_mobile_number")
	private String mobileNumber; //武馆负责人电话
	@Column(length = 100,name = "kf_state")
	private int state; //武馆状态(0:冻结 1:激活)
	@Column(length = 200,name = "kf_dojo_address")
	private String dojoAddress; //武馆地址
	@Column(length = 200,name = "kf_dojo_latitude")
	private String dojoLatitude; //地址所在纬度
	@Column(length = 200,name = "kf_dojo_longitude")
	private String dojoLongitude; //地址所在经度
	@Column(length = 200,name = "kf_dojo_geo_code")
	private String geoCode; //根据经纬度计算的geohash编码
	@Column(length = 200,name = "kf_photo_list")
	private String photoList;  //武馆相册目录
	@Column(length = 200,name = "kf_dojo_portrait")
	private String dojoPortrait; //武馆头像
	@Column(name = "kf_dojo_register_time")
	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	private Date dojoRegisterTime; //武馆创建时间
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kf_person_id")
	private Person person;  //武馆负责人
}
