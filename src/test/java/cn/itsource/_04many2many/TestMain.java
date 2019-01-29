package cn.itsource._04many2many;

import cn.itsource.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class TestMain {
    @Test
    public void test(){
        JPAUtil.getEntityManager();
    }
    
    
    @Test
    public void testSave(){

        Teacher teacher1 = new Teacher();
        teacher1.setName("吕老师");

        Teacher teacher2 = new Teacher();
        teacher2.setName("莫老湿");


        Student s1 = new Student();
        s1.setName("王天霸");

        Student s2 = new Student();
        s2.setName("赵日天");

        Student s3 = new Student();
        s3.setName("上天了");

        Student s4 = new Student();
        s4.setName("金莲");

        //老师维护关系
        teacher1.getStudents().add(s1);
        teacher1.getStudents().add(s2);
        teacher2.getStudents().add(s3);
        teacher2.getStudents().add(s4);

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
//        entityManager.persist(s1);
//        entityManager.persist(s2);
//        entityManager.persist(s3);
//        entityManager.persist(s4);

        entityManager.getTransaction().commit();

    }


    @Test
    public void testFind(){
        testSave();
        EntityManager entityManager = JPAUtil.getEntityManager();
        Teacher teacher = entityManager.find(Teacher.class, 1L);
        System.out.println(teacher);
        System.out.println(teacher.getStudents());
    }

    @Test
    public void testRemove(){
        testSave();
        EntityManager entityManager = JPAUtil.getEntityManager();
        Teacher teacher = entityManager.find(Teacher.class, 1L);
        entityManager.getTransaction().begin();
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();

    }

    @Test
    public void testRemove2(){

        testSave();
        EntityManager entityManager = JPAUtil.getEntityManager();
        Student student = entityManager.find(Student.class, 1L);
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }
}
