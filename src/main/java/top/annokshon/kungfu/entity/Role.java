package top.annokshon.kungfu.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author kshon
 * @description  角色权限表
 * @date 2019-08-26 20:06
 */
@Entity
@Table(name = "kf_role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kf_id")
    private int id;

    @Column(length = 100,name = "kf_name")
    private String name;  //角色名称


}
