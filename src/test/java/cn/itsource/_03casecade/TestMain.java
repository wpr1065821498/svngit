package cn.itsource._03casecade;

import cn.itsource.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

public class TestMain {
    @Test
    public void test(){
        JPAUtil.getEntityManager();
    }

    @Test
    public void testSave(){
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setNumber("203432423432424324");

        OrderItem item1 = new OrderItem();
        item1.setNum(new BigDecimal("2"));
        item1.setPrice(new BigDecimal("3"));
        item1.setAmount(item1.getNum().multiply(item1.getPrice()));
        item1.setProductName("产品1");

        OrderItem item2 = new OrderItem();
        item2.setNum(new BigDecimal("21"));
        item2.setPrice(new BigDecimal("32"));
        item2.setAmount(item2.getNum().multiply(item2.getPrice()));
        item2.setProductName("产品2");

        //订单把订单明细添加进来（仅仅只是达到级联的效果）
        order.getItems().add(item1);
        order.getItems().add(item2);

        //多方维护关系
        item1.setOrder(order);
        item2.setOrder(order);

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Test
    public void delete(){
        testSave();
        EntityManager entityManager = JPAUtil.getEntityManager();
        Order order = entityManager.find(Order.class, 1L);
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }


    @Test
    public void testDelete2(){
        testSave();
        EntityManager entityManager = JPAUtil.getEntityManager();
        Order order = entityManager.find(Order.class, 1L);
        entityManager.getTransaction().begin();
        order.getItems().clear();
        entityManager.getTransaction().commit();
    }

}
