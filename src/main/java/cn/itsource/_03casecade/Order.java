package cn.itsource._03casecade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    //订单编号
    private String number;
    //订单时间
    private Date createDate;

    /*
    \cascade = CascadeType.PERSIST  级联保存   保存一方的时候，与之相关的多方一并会被保存起来
    * cascade = CascadeType.MERGE   级联修改
    *cascade = CascadeType.REMOVE   级联删除   删除一方的时候，它会把和一方有关系的数据全部删除掉
    *           在使用级联删除一定要慎用，因为一旦用不好，它会把你不想删除的数据一并删除掉的、
    *
    *           级联删除使用场景： 一般用在组合关系
    *
    *
    * cascade = CascadeType.ALL  包含了级联添加/级联修改/级联删除
    *
    *  orphanRemoval = true  孤儿删除      1方可以删除多方，1方依然存在
    *
    *
    *       如果只配置了级联删除，没有配置 orphanRemoval = true
    *       删除的效果：  删除一方，它会把和一方有关联关系的明细全部删除，  但是如果你想保留1方，只想删除多方，它是达不到这个效果的
    *
    *       如果配置了级联删除，  也配置了orphanRemoval = true
    *
    *       删除的效果： 删除一方，它会把和一方有关联关系的明细全部删除， 如果你想保留1方，只想删除多方，它是能达到这个效果的
    *
    * */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)// 最强级联
    private List<OrderItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
