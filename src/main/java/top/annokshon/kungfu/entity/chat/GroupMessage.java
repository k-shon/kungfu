package top.annokshon.kungfu.entity.chat;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kshon
 * @description  群聊消息
 * @date 2019-10-09 23:13
 */
@Data
@Entity
@Table(name = "kf_group_message")
public class GroupMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kf_id")
    private int id;
    @Column(length = 2000,name = "kf_context")
    private String context;  //聊天内容
    @Column(length = 1,name = "kf_status")
    private int status;  //聊天内容状态(0:未接收,1:已接收)
    @Column(name = "kf_send_time")
    @DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
    private Date sendTime; //发送时间
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kf_person_id")
    private Person person;  //发送人
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kf_group_chat_id")
    private GroupChat groupChat;  //群聊id
}
