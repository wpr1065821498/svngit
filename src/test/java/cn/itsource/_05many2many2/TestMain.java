package cn.itsource._05many2many2;

import cn.itsource._05_many2many2.Role;
import cn.itsource._05_many2many2.User;
import cn.itsource.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.EnumSet;

public class TestMain {
    @Test
    public void test(){
        JPAUtil.getEntityManager();
    }

    @Test
    public void testSave(){
        User user1 = new User();
        user1.setName("user1");

        User user2 = new User();
        user2.setName("user2");

        Role r1 = new Role();
        r1.setName("角色1");
        Role r2 = new Role();
        r2.setName("角色2");
        Role r3 = new Role();
        r3.setName("角色3");

        //维护关系
        user1.getRoles().add(r1);
        user1.getRoles().add(r2);

        user2.getRoles().add(r2);
        user2.getRoles().add(r3);



        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user1);
        entityManager.persist(user2);


        entityManager.getTransaction().commit();
    }


    /**
     * 在使用many2many的时候，级联删除一定要用好，慎用，用不好的话，你三张表中所有的数据都要删完
     */
    @Test
    public void delete(){
        testSave();
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, 1L);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }


    /**
     * 在使用框架的时候，想要在domain中打印toString,都不要打印关联对象，很可能出现一些错误，就算没有出现错误，对你的性能都是比较差的
     */
    @Test
    public void testFind(){
        testSave();
        System.out.println("================================");
        EntityManager entityManager = JPAUtil.getEntityManager();
        User user = entityManager.find(User.class, 1L);
        System.out.println(user);
    }


}
