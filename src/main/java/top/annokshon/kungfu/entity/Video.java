package top.annokshon.kungfu.entity;

import javax.persistence.*;

/*
 * 视频
 */
@Entity
@Table(name = "tb_video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
