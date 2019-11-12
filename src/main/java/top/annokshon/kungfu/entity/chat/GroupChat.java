package top.annokshon.kungfu.entity.chat;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kshon
 * @description 群聊表
 * @date 2019-10-09 23:06
 */
@Data
@Entity
@Table(name = "kf_group_chat")
public class GroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kf_id")
    private int id;
    @Column(length = 100,name = "kf_name")
    private String name;  //群聊名称
    @Column(length = 200,name = "kf_icon")
    private String icon;  //群聊图标
    @Column(length = 300,name = "kf_notice")
    private String notice;  //群聊公告
    @Column(length = 300,name = "kf_intro")
    private String intro;  //群聊简介
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kf_admin_person_id")
    private Person adminPerson;  //群主
    @Column(name = "kf_create_time")
    @DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
    private Date createtime; //创建时间
}
