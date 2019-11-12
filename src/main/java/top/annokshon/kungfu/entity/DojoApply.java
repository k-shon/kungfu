package top.annokshon.kungfu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "kf_dojo_apply")
@Data
public class DojoApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kf_id")
    private int id;
    @Column(length = 100, name = "kf_apply_state")
    private int applyState;  //1代表成功，0代表正在申请，-1代表销毁
    @Column(length = 100, name = "kf_apply_price")
    private int applyPrice;  //报名费
    @Column(length = 100, name = "kf_pay_state")
    private int payPtate; //支付状态，1:成功；0:失败
    @DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
    @Column(name = "kf_apply_time")
    private Date applyTime; //报名时间
    @ManyToOne
    @JoinColumn(name = "kf_person_id")
    private Person person; //报名者的个人信息
    @ManyToOne
    @JoinColumn(name = "kf_dojo_id")
    private Dojo dojo; //所报名的武馆

}
