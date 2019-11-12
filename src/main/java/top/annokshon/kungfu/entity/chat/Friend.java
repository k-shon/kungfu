package top.annokshon.kungfu.entity.chat;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;

import javax.persistence.*;

/**
 * @author kshon
 * @description
 * @date 2019-10-09 20:25
 */
@Data
@Entity
@Table(name = "kf_friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kf_id")
    private int id;
    @Column(length = 100,name = "kf_name")
    private String name;  //好友备注昵称
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kf_person_id")
    private Person person;  //自己的personid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kf_friend_id")
    private Person friend;  //好友的personid

    private int unRead;  //未读消息，不入库
}
