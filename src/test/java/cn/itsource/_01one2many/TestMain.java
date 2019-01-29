package cn.itsource._01one2many;

import cn.itsource.util.JPAUtil;
import org.hibernate.collection.internal.PersistentSet;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestMain {
    @Test
    public void test(){
        JPAUtil.getEntityManager();
    }

    /**
     * 先保存1方，再保存多方一共发送5条sql语句：
     *              第一条sql语句： 先保存  dept表，这时候1方在维护关系，结果多方还没有值
     *              第二条与第三条sql语句： 保存 employee表，多方没有维护关系，所以外键依然为null
     *              当commit的时候： dept与employee都有数据了，它会额外会发送2条sql语句去维护关系
     *
     *
     *
     * 先保存多方，在保存1方一共发送5条sql语句：
     *          第一条和第二条sql语句： 先保存employee，这时候多方不维护关系，所以外键依然为null
     *          第三条sql语句： 保存dept
     *
     *          commit：  发现dept与employee都有值了，这时候会发送额外2条sql语句去更新关系
     *
     *
     * 总结： 所以说单向的一对多用的不多，因为不管先保存1方还是先保存多方它都会发送5条sql语句
     *
     */
    @Before
    public void testSave(){
        Dept dept = new Dept();
        dept.setName("人才部");

        Employee employee1 = new Employee();
        employee1.setName("赵日天");

        Employee employee2 = new Employee();
        employee2.setName("若包了");

        //维护关系
        dept.getEmployees().add(employee1);
        dept.getEmployees().add(employee2);


        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        //再保存多方
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        //先保存一方
        entityManager.persist(dept);

        entityManager.getTransaction().commit();

    }

//    @Test
//    public void testFind1(){
//        EntityManager entityManager = JPAUtil.getEntityManager();
//        Dept dept = entityManager.find(Dept.class, 1L);
//        System.out.println(dept);
//
//
//        //拿到的这个set集合不再是我们最单纯的set类型了
//        Set<Employee> employees = dept.getEmployees();
//        System.out.println(employees);
//
//
//        System.out.println(employees.getClass());
//    }
    @Test
    public void testFind1(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        Dept dept = entityManager.find(Dept.class, 1L);
        System.out.println(dept);


        //拿到的这个set集合不再是我们最单纯的set类型了
        List<Employee> employees = dept.getEmployees();
        System.out.println(employees);


        System.out.println(employees.getClass());
    }
}
