package cn.itsource._02one2many2;

import cn.itsource.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Set;

public class TestMain {
    @Test
    public void test(){
        JPAUtil.getEntityManager();
    }

    /**
     * 先保存1方再保存多方
     */
    @Test
    public void testSave(){

        ProductDir dir = new ProductDir();
        dir.setName("电视机");
        Product p1 = new Product();
        p1.setName("长虹电视机");

        Product p2 = new Product();
        p2.setName("康佳电视机");

        //维护关系（一方维护关系）
        dir.getProducts().add(p1);
        dir.getProducts().add(p2);

        //维护关系(多方维护)
        p1.setDir(dir);
        p2.setDir(dir);
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(dir);// 第一条sql语句，保存产品类型，它想维护关系，这时候，产品还没开始保存
        entityManager.persist(p1);// 第二条sql语句，正常保存产品    产品类型依然有值了，关系正常维护
        entityManager.persist(p2);// 第三条sql语句，正常保存产品    产品类型依然有值了，关系正常维护
        entityManager.getTransaction().commit();//提交的时候，它会额外发送2条sql语句进行维护关系（这2条sql语句是1方发出来的）----》这2条sql语句是多余的
    }


    /**
     * \先保存多方，再保存1方
     */
    @Test
    public void testSave1(){
        ProductDir dir = new ProductDir();
        dir.setName("电视机");

        Product p1 = new Product();
        p1.setName("长虹电视机");

        Product p2 = new Product();
        p2.setName("康佳电视机");

        //维护关系（一方维护关系）
        dir.getProducts().add(p1);
        dir.getProducts().add(p2);

        //维护关系(多方维护)
        p1.setDir(dir);
        p2.setDir(dir);
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(p1);// 第一条sql语句 保存产品 ， 它想维护关系  结果dept此时此刻没值
        entityManager.persist(p2);//第二条sql语句 保存产品 ， 它想维护关系  结果dept此时此刻没值
        entityManager.persist(dir);// 第三条sql语句  保存产品类型   它想维护关系， 维护不了，因为外键在多方
        entityManager.getTransaction().commit();    //多方要发送2条sql语句去维护关系
                                                    //1方要发送2条SQL语句去维护关系（多发送2条sql语句，重复了，多余的）
    }

    /**
     * 从多方找到1方
     */
    @Test
    public void testFind(){
        testSave();
        System.out.println("============================================");
        EntityManager entityManager = JPAUtil.getEntityManager();
        Product product = entityManager.find(Product.class, 1L);
        System.out.println(product);

        System.out.println(product.getDir());
    }


    @Test
    public void testFind2(){
        testSave();
        System.out.println("============================================");
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDir dir = entityManager.find(ProductDir.class, 1L);
        System.out.println(dir);

        Set<Product> products = dir.getProducts();
        //判断集合是否有值
        System.out.println(products.size());
        System.out.println(dir.getProducts());

    }


}
