package cn.itsource._06one2one2;

import javax.persistence.*;

@Entity
@Table(name="t_qq")
public class QQ {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    @OneToOne(mappedBy = "qq")
    private QQZone qqZone;
}
