package cn.itsource._06one2one2;

import javax.persistence.*;
//数据的传输
@Entity
@Table(name="t_qqzone")
public class QQZone {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(optional = false)// 关联对象设置为不能为空
    @JoinColumn(name="qq_id")
    private QQ qq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QQ getQq() {
        return qq;
    }

    public void setQq(QQ qq) {
        this.qq = qq;
    }
}
