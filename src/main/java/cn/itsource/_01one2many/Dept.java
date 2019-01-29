package cn.itsource._01one2many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Dept {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    /**
     * 注意：申明一对多的时候，必须申明集合
     * 要么申明set要么申明List
     * <p>
     * 在申明集合的时候，该集合一定要new出来，因为你不new出来的话，会为自己留下很多坑，很容易造成空指针异常
     * <p>
     * <p>
     * 一对多默认就是一个延迟加载
     * 如果记不住，何时为及时加载何时为延迟加载，以后我们统一都在关系这里配置一个fetch = FetchType.LAZY
     * <p>
     * <p>
     * 记住一句话： 凡事集合都是延迟加载
     * <p>
     * <p>
     * 注意： 以后申明集合类型的时候，必须是接口，不能是实现类
     * 原因：
     * <p>
     * set
     * |___HashSet
     * |__PersistentSet
     *
             * @OneToMany(fetch = FetchType.LAZY)
     * @JoinColumn(name = "dept_id")//外键对应的列名
     * private Set<Employee> employees = new HashSet<>();
     */
    @OneToMany
    @JoinColumn(name="dept_id")
    @OrderBy("name desc ")
    private List<Employee> employees = new ArrayList<>();

    /**
     * 以后我们定义一对多的时候，到底应该定义为set还是List呢？
     *      你要根据你的业务来说
     *              如果你存储的数据不能出现重复，那就建议使用set
     *              如果你存储的数据是能允许重复的，并且偶尔想要进行排序那可以使用list
     *
     *              如果你只是单纯的想要配置一下一对多，那你就随便找一个吧
     * @return
     */




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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
