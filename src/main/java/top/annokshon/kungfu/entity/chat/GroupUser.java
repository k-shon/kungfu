package top.annokshon.kungfu.entity.chat;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kshon
 * @description  群聊成员
 * @date 2019-10-09 23:09
 */
@Data
@Entity
@Table(name = "kf_group_user")
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kf_id")
    private int id;
    @Column(length = 100,name = "kf_group_nick")
    private String groupNick;  //群聊昵称
    @Column(name = "kf_create_time")
    @DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
    private Date createtime; //创建时间
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kf_person_id")
    private Person person;  //成员
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kf_group_chat_id")
    private GroupChat groupChat;  //群聊id
}
