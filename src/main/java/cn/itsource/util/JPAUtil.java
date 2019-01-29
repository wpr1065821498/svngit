package cn.itsource.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//上传传输，，在测试一波
public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    static{
        try {
            entityManagerFactory =  Persistence.createEntityManagerFactory("cn.itsource.jpa");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析xml异常。。。。。。");
        }
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
