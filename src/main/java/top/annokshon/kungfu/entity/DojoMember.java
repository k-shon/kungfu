package top.annokshon.kungfu.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
/*
 * 武馆成员信息
 */
@Entity
@Table(name = "kf_dojo_member")
@Data
public class DojoMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kf_id")
	private int id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kf_person_id")
	private Person person;  //成员个人信息
	@Column(length = 100,name = "kf_position")
	private String position;  //成员职位
	@Column(length = 100,name = "kf_state")
	private String state;  //成员状态
	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	@Column(name = "kf_in_time")
	private Date inTime;  //入馆时间
	@Column(length = 100,name = "kf_daytime")
    private int daytime;  //在馆总时长（按天算）
}
